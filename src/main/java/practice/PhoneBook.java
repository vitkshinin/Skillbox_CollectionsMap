package practice;

import java.util.*;

public class PhoneBook {
    public TreeMap<String, String> book = new TreeMap<>();

    public boolean isName(String input) {
        String regexName = "[А-ЯЁа-яё]+";
        return input.matches(regexName);
    }

    public boolean isPhoneNumber(String input) {
        String regexPhone = "[7][0-9]{10}";
        return input.matches(regexPhone);
    }


    public void addContact(String phone, String name) {
        // проверьте корректность формата имени и телефона
        // (рекомедуется написать отдельные методы для проверки является строка именем/телефоном)
        // если такой номер уже есть в списке, то перезаписать имя абонента
        if (isPhoneNumber(phone) == true && isName(name) == true) {
            book.put(phone, name);
        }
    }


    public String getContactByPhone(String phone) {
        // формат одного контакта "Имя - Телефон"
        // если контакт не найдены - вернуть пустую строку
        String result = "";

        if (book.containsKey(phone)) {
            return book.get(phone) + " - " + phone;
        } else {
            return "";
        }
    }

    public Set<String> getContactByName(String name) {
        // формат одного контакта "Имя - Телефон"
        // если контакт не найден - вернуть пустой TreeSet

        Set<String> contact = new TreeSet<>();
        for (String key : book.keySet()) {
            if (book.containsValue(name)) {
                contact.add(name + " - " + key);
                return contact;
            }
        }
        return contact;
    }

    public Set<String> getAllContacts() {
        // формат одного контакта "Имя - Телефон"
        // если контактов нет в телефонной книге - вернуть пустой TreeSet
        Map<String, TreeSet<String>> nameToPhones = new HashMap<>();
        for (String key : book.keySet()) {
            String name = book.get(key);
            if (!nameToPhones.containsKey(name)) {
                nameToPhones.put(name, new TreeSet<>());
            }
            nameToPhones.get(name).add(key);
        }

        Set<String> result = new HashSet<>();
        for (String key : nameToPhones.keySet()) {
            String resultString = key + " - " + nameToPhones.get(key);
            result.add(resultString.replaceAll("[\\[\\]]", ""));
        }

        return result;
    }

    // для обхода Map используйте получение пары ключ->значение Map.Entry<String,String>
    // это поможет вам найти все ключи (key) по значению (value)
    /*
        for (Map.Entry<String, String> entry : map.entrySet()){
            String key = entry.getKey(); // получения ключа
            String value = entry.getValue(); // получения ключа
        }
    */
}