import java.sql.Statement;
import java.util.*;

class Contact {
    private int contact_id;
    private String name;
    private long number;
    private static int contact_id_count = 0;
    private static HashMap<Integer,Contact> contact_list = new HashMap<>();

    public Contact(){
        this.contact_id=++contact_id_count;
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

    public static HashMap<Integer, Contact> getContacts() {
        return contact_list;
    }

    public String toString(){
        return getContact_id()+"\t"+getName()+"\t"+getNumber()+"\n";
    }

    public static String createContact(String[] arguments){
        Contact contact = new Contact();
        contact.setName(arguments[0]);
        contact.setNumber(Integer.parseInt(arguments[1]));
        contact_list.put(contact.contact_id,contact);
        return "Contact successfully added!\nContact id : "+contact.getContact_id();
    }

    public static String deleteContact(String[] arguments){
        if(arguments.length==0){
            contact_list = new HashMap<>();
            return "All contacts successfully deleted!";
        }
        for(int key : contact_list.keySet()){
            if(key == Integer.parseInt(arguments[0])){
                contact_list.remove(key);
                return "Contact successfully deleted!";
            }
        }
        return "Invalid contact id! Cannot delete contact!";
    }

    public static String updateContact(String[] arguments){
        String[] arr = arguments[1].split(":");
        for(Map.Entry<Integer,Contact> entry : contact_list.entrySet()){
            if(entry.getKey() == Integer.parseInt(arguments[0])){
                if(arr[0].equals("number")) {
                    entry.getValue().setNumber(Long.parseLong(arr[1]));
                    return "Contact number successfully updated!";
                }
                else if(arr[0].equals("name")){
                    entry.getValue().setName(arr[1]);
                    return "Contact name successfully updated!";
                }
            }
        }
        return "Invalid contact id! Cannot update contact!";
    }

    public static String getContacts(String[] arguments){
        if(arguments.length==0){
            return contact_list.values().toString();
        }
        for(Map.Entry<Integer,Contact> entry : contact_list.entrySet()){
            if(entry.getKey() == Integer.parseInt(arguments[0])){
                return entry.getValue().toString();
            }
        }
        return "Invalid contact id! No such contact exists!";
    }
}

class Message {
    private int message_id;
    private static int message_id_count;
    private String text;
    private String type;//incoming or outgoing

    public Message() {
        this.message_id = ++message_id_count;
    }

    public int getMessage_id() {
        return message_id;
    }

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
        return getType()+" : "+getText()+"\n";
    }
}

class Chat {
    private int chat_id;
    private static int chat_id_count = 0;
    private Contact contact;
    private boolean archived;//active or archive
    private ArrayList<Message> messages = new ArrayList<>();
    private static HashMap<Integer,Chat> chat_list = new HashMap<>();

    public Chat() {
        this.chat_id = ++chat_id_count;
        this.archived = false;
    }

    public int getChat_id() {
        return chat_id;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public static HashMap<Integer, Chat> getChat_list() {
        return chat_list;
    }

    public static void setChat_list(HashMap<Integer, Chat> chat_list) {
        Chat.chat_list = chat_list;
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }

    public String setMessage(String text,String type) {
        Message message = new Message();
        message.setText(text);
        message.setType(type);
        messages.add(message);
        return "Message sent successfully!";
    }

    public boolean isArchived() {
        return archived;
    }

    public void setArchived(boolean value) {
        archived = value;
    }

    public String toString(){
        return "contact name : "+contact.getName()+"\nmessages : \n"+getMessages()+"\n\n";
    }

    public static String getChats(String[] arguments){
        if(arguments.length==0){
            String result="";
            for(Map.Entry<Integer,Chat> entry : chat_list.entrySet()){
                if(!entry.getValue().isArchived()){
                    result+=entry.getValue().toString();
                }
            }
            return result;
        }
        for(Map.Entry<Integer,Chat> entry : chat_list.entrySet()){
            if(entry.getKey() == Integer.parseInt(arguments[0])){
                return entry.getValue().toString();
            }
        }
        return "No chat exists for this id!!";
    }

    public static String deleteChat(String[] arguments){
        if(arguments.length==0){
            chat_list = new HashMap<>();
            return "All chats successfully deleted!";
        }
        for(int key : chat_list.keySet()){
            if(key == Integer.parseInt(arguments[0])){
                chat_list.remove(key);
                return "Chat successfully deleted!";
            }
        }
        return "No chat exists for this id! Cannot delete chat!";
    }

    public static String updateChat(String[] arguments){
        for(Map.Entry<Integer,Chat> entry : chat_list.entrySet()){
            if(entry.getKey() == Integer.parseInt(arguments[0])){
                if(arguments[1].equals("archive")) {
                    entry.getValue().setArchived(true);
                    return "Chat is archived successfully!!";
                }
                if(arguments[1].equals("unarchive")){
                    entry.getValue().setArchived(false);
                    return "Chat is unarchived successfully!!";
                }
            }
        }
        return "No chat exists for this id! Cannot update chat status!";
    }

    public static String sendMessage(String[] arguments){
        int contact_id = Integer.parseInt(arguments[0]);
        String text = arguments[1];
        String type;
        if(arguments.length==2){
            type = "out";
        }
        else {
            type = arguments[arguments.length - 1];
        }
        Chat chat;
        if(!chat_list.containsKey(contact_id) && Contact.getContacts().containsKey(contact_id)){
            chat = new Chat();
            chat.setContact(Contact.getContacts().get(contact_id));
            chat_list.put(contact_id,chat);
        }
        else if(chat_list.containsKey(contact_id)){
            chat = chat_list.get(contact_id);
        }
        else{
            return "No chat exists for this id! Cannot send message!";
        }
        return chat.setMessage(text,type);
    }
}
class Status{
    private int status_id;
    private static int status_id_count = 0;
    private int status_info_id = 0;
    private Contact contact;
    private boolean muted = false;
    private HashMap<Integer,String> status_info = new HashMap<>();
    private static HashMap<Integer,Status> status_list = new HashMap<>();

    public Status() {
        this.status_id = ++status_id_count;
    }

    public int getStatus_id() {
        return status_id;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public boolean isMuted() {
        return muted;
    }

    public void setMuted(boolean mute) {
        this.muted = mute;
    }

    public static HashMap<Integer, Status> getStatus_list() {
        return status_list;
    }

    public HashMap<Integer, String> getStatus_info() {
        return status_info;
    }

    public String toString(){
        return this.getContact().getName()+"\n"+status_info.values();
    }

    public static String getStatus(String[] arguments){
        if(arguments.length==0){
            String result="";
            for(Map.Entry<Integer,Status> entry : status_list.entrySet()){
                if(!entry.getValue().isMuted()){
                    result+=entry.getValue().toString();
                }
            }
            return result;
        }
        for(Map.Entry<Integer,Status> entry : status_list.entrySet()){
            if(entry.getKey() == Integer.parseInt(arguments[0])){
                return entry.getValue().toString();
            }
        }
        return "No status exists for this id!!";
    }

    public String setStatus_info(String text) {
        status_info.put(++status_info_id,text);
        return "Status successfully uploaded!";
    }

    public static String setStatus(String[] arguments) {
        int contact_id = Integer.parseInt(arguments[0]);
        String text = arguments[1];
        Status status;

        if(!status_list.containsKey(contact_id) && Contact.getContacts().containsKey(contact_id)){
            status = new Status();
            status.setContact(Contact.getContacts().get(contact_id));
            status_list.put(contact_id,status);
        }
        else if(status_list.containsKey(contact_id)){
            status = status_list.get(contact_id);
        }
        else{
            return "No contact exists for this id! Cannot set status!";
        }
        return status.setStatus_info(text);
    }

    public String deleteStatus_info(int status_info_id){
        for(int key : status_info.keySet()){
            if(key == status_info_id){
                status_info.remove(status_info_id);
                return "Status successfully deleted!";
            }
        }
        return "No such status id exists!";
    }

    public static String deleteStatus(String[] arguments){
        if(arguments.length==0){
            status_list = new HashMap<>();
            return "All status successfully deleted!";
        }
        for(int key : status_list.keySet()){
            if(key == Integer.parseInt(arguments[0])){
                Status status = status_list.get(Integer.parseInt(arguments[0]));
                return status.deleteStatus_info(Integer.parseInt(arguments[1]));
            }
        }
        return "No status exists for this id! Cannot delete status!";
    }

    public static String sendReply(String[] arguments){
        if(arguments.length>=2) {
            int contact_id = Integer.parseInt(arguments[1]);
            for(Map.Entry<Integer,Status > entry : status_list.entrySet()){
                if(entry.getKey() == contact_id){
                    Chat chat;
                    if(Chat.getChat_list().containsKey(contact_id)) {
                        chat = Chat.getChat_list().get(contact_id);
                    }
                    else{
                        chat = new Chat();
                        chat.setContact(Contact.getContacts().get(contact_id));
                        Chat.getChat_list().put(contact_id,chat);
                    }
                    return chat.setMessage(arguments[3],"status reply");
                }
            }
        }
        return "Invalid id! Please enter valid id to reply!";
    }

    public static String updateStatus(String[] arguments){
        for(Map.Entry<Integer,Status> entry : status_list.entrySet()){
            if(entry.getKey() == Integer.parseInt(arguments[0])){
                if(arguments[1].equals("mute")) {
                    entry.getValue().setMuted(true);
                    return "Chat is archived successfully!!";
                }
                if(arguments[1].equals("unmute")){
                    entry.getValue().setMuted(false);
                    return "Chat is unarchived successfully!!";
                }
            }
        }
        return "No status exists for this id! Cannot mute status!";
    }
}


public class WhatsappURL {

    public static void processCommand(String url){
        String[] words = url.split(" /",2);
        String method = words[0];
        String[] arguments = words[1].split("/");
        String entity = arguments[0];
        arguments = Arrays.copyOfRange(arguments,1,arguments.length);
        String result = "";
        if(entity.equals("contacts")){
            if(method.equals("post")){
                result = Contact.createContact(arguments);
            }
            else if(method.equals("delete")){
                result = Contact.deleteContact(arguments);
            }
            else if(method.equals("put")){
                result = Contact.updateContact(arguments);
            }
            else if(method.equals("get")){
                result = Contact.getContacts(arguments);
            }
        }
        if(entity.equals("chats")){
            if(method.equals("post")){
                result = Chat.sendMessage(arguments);
            }
            else if(method.equals("delete")){
                result = Chat.deleteChat(arguments);
            }
            else if(method.equals("put")){
                result = Chat.updateChat(arguments);
            }
            else if(method.equals("get")){
                result = Chat.getChats(arguments);
            }
        }
        if(entity.equals("status")){
            if(method.equals("post")){
                if(arguments[0].equals("reply")){
                    result = Status.sendReply(arguments);
                }
                else {
                    result = Status.setStatus(arguments);
                }
            }
            else if(method.equals("delete")){
                result = Status.deleteStatus(arguments);
            }
            else if(method.equals("put")){
                result = Status.updateStatus(arguments);
            }
            else if(method.equals("get")){
                result = Status.getStatus(arguments);
            }
        }
        System.out.println(result);
    }

    public static void main(String[] ar){
        Scanner input = new Scanner(System.in);
        do{
            System.out.println("Enter the command : ");
            String url = input.nextLine();
            if(url.equals("exit")){
                break;
            }
            processCommand(url);
        }while(true);
    }
}