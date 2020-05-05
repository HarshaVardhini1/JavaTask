import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

class Contact {
    private static int contact_id_count = 0;
    private int contact_id;
    private String name;
    private long number;

    public Contact(){
        this.contact_id = ++contact_id_count;
    }

    public int getContact_id() {
        return contact_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public String toString(){
        return "Contact id : "+contact_id+"\nContact name : "+name+"\nContact number : "+getNumber();
    }
}

class Message {
    private String text;
    private String type;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String toString(){
        return "\n"+getType()+" : "+getText()+"";
    }
}

class Chat {
    private static int chat_id_count=0;
    private int chat_id;
    private Contact contact;
    private ArrayList<Message> messages;

    public Chat(){
        messages = new ArrayList<>();
        this.chat_id=++chat_id_count;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public String getMessages() {
        return Arrays.toString(messages.toArray());
    }

    public void setMessage(Message message) {
        messages.add(message);
    }

    public String toString(){
        return "contact name : "+contact.getName()+"\nmessages : \n"+getMessages()+"\n\n";
    }
}

public class Whatsapp {
    static ArrayList<Contact> contacts = new ArrayList<>();
    static ArrayList<Chat> chats = new ArrayList<>();
    static Scanner read = new Scanner(System.in);

    static Chat current_chat;
    static Contact current_contact;

    public static void checkIfContactExists(String[] words){
        if(words.length>2) {
            current_contact = specificContact(Integer.parseInt(words[2]));
            current_chat = specificChat(Integer.parseInt(words[2]));
            if(current_contact==null){
                System.out.println("Enter a valid contact id!");
            }
            if(current_chat==null){
                current_chat = new Chat();
            }
        }
        else {
            current_chat = new Chat();
            current_contact = new Contact();
        }
    }

    public static void sendMessage(Chat chat){
        System.out.println("Enter the message : ");
        Message message = new Message();
        message.setText(read.nextLine());
        System.out.println("Enter in/out : ");
        message.setType(read.nextLine());
        chat.setMessage(message);
        if(!chats.contains(chat)){
            chats.add(chat);
        }
        System.out.println(chat);
    }

    public static void createContact(Contact contact){
        System.out.println("Enter the name of the contact : ");
        contact.setName(read.nextLine());
        System.out.println("Enter the contact number : ");
        contact.setNumber(read.nextLong());
        read.nextLine();
        contacts.add(contact);
        System.out.println(contact);
    }

    public static Contact specificContact(int contact_id){
        for(Contact contact : contacts){
            if(contact_id==contact.getContact_id()){
                return contact;
            }
        }
        return null;
    }

    public static void editContact(Contact contact){
        System.out.println("Do you want to change the name of the contact ? (y/n)");
        if(read.next().equals("y")){
            System.out.println("Enter the new name : ");
            contact.setName(read.nextLine());
        }
        System.out.println("Do you want to change the number of the contact ? (y/n)");
        if(read.next().equals("y")){
            System.out.println("Enter the new number : ");
            contact.setNumber(read.nextLong());
            read.nextLine();
        }
    }

    public static void displayContacts(){
        for(Contact contact : contacts){
            System.out.println(contact);
        }
    }

    public static Chat specificChat(int contact_id){
        for(Chat chat : chats){
            if(contact_id==chat.getContact().getContact_id()){
                return chat;
            }
        }
        return null;
    }

    public static void displayChats(){
        for(Chat chat : chats){
            System.out.println(chat);
        }
    }

    public static void post(String[] words){
        if (words[1].equals("contacts")) {
            createContact(current_contact);
        }
        else if (words[1].equals("chats")) {
            if(current_contact.getName()!=null) {
                sendMessage(current_chat);
            }
            else{
                System.out.println("Please enter a valid contact id!");
            }
        }
    }

    public static void get(String[] words){
        if(words[1].equals("contacts")){
            if(words.length==2){
                displayContacts();
            }
            else {
                System.out.println(current_contact);
            }
        }
        else if(words[1].equals("chats")){
            if(words.length==2){
                displayChats();
            }
            else {
                System.out.println(current_chat);
            }
        }
    }

    public static void delete(String[] words){
        if(words[1].equals("contacts")){
            if(words.length==2) {
                contacts.clear();
            }
            else{
                contacts.remove(current_contact);
            }
        }
        else if(words[1].equals("chats")){
            if(words.length==2) {
                chats.clear();
            }
            else{
                chats.remove(current_chat);
            }
        }
    }

    public static void put(String[] words){
        if(words[1].equals("contacts")){
            if(words.length>2){
                editContact(current_contact);
            }
        }
    }

    public static void main(String[] ar) {
        do{
            System.out.print("Enter the command : ");
            String input = read.nextLine();
            String[] words = input.split("/");

            checkIfContactExists(words);

            if(input.startsWith("post")) {
                post(words);
            }
            else if(input.startsWith("get")){
                get(words);
            }
            else if(input.startsWith("delete")){
                delete(words);
            }
            else if(input.startsWith("put")){
                put(words);
            }
            if(read.next().equals("exit")){
                break;
            }
        }
        while(true);
    }
}

