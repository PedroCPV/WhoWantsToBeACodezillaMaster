package org.academiadecodigo.codezillas.WhoWhatsBeCodezillaMaster.Questions;

import java.util.HashMap;

public class QuestionsBucket {

    private int numOfQuestions;
    private HashMap<Integer, Question> hashMap;

    public QuestionsBucket() {
        hashMap = new HashMap<>();
        numOfQuestions = 0;
    }

    public void addNewQuestion(String question, String optionA, String optionB, String optionC, String optionD, int validIndex) {
        Question newQuestion = new Question(question, optionA, optionB, optionC, optionD, validIndex);
        numOfQuestions++;
        hashMap.put(numOfQuestions, newQuestion);
    }

    public HashMap<Integer, Question> getHashMap() {
        return hashMap;
    }

    public void questionsInit() {
        addNewQuestion("The most basic component of 'em all that makes a computer tick: ", "Operating System?", "The CPU?", "RAM perhaps?", "Could it be the electricity?", 4);
        addNewQuestion("What component has built-in electronic circuits?", "Println Circuit Board", "Printed Circle Board", "Printer Circle Board", "Printed Circuit Board", 4);
        addNewQuestion("What are the two main numeral systems a \"modern\" computer operates with?","Lobster & Cockroach", "NS-1 & NS-2", "Binary & Hexadecimal", "Hexabinary & Hexadecimal", 3);
        addNewQuestion("What guy invented the numeral system that almost the entire western world uses nowadays?", "Al-Guaritzmi", "Al-Kwaridzli", "Al-Khwarizmi", "Al-Garizmy", 3);
        addNewQuestion("Of the following options, choose the one that best fits Boolean Logic: ", "And, Or & Not", "True & False", ".equals() & \"==\"", "On/Off & 0/1", 1);
        addNewQuestion("What is the order in which boolean logic is evaluated inside a statement?", "NOT comes first, then OR and finally AND", "NOT comes first, then AND and finally OR", "Whichever comes first, like in a queue. FIFO style", "NOT comes first, then OR or AND have the same precedence", 2);
        addNewQuestion("Which design allowed for more flexible program execution and established the base for all modern computers?", "The Turing Machine Processing Architecture", "The George Boole Design Pattern", "The Von Neumann Architecture", "The Memory Subsystems Design", 3);
        addNewQuestion("Choose (wisely) the most accurate answer regarding Operating Systems: ", "Controls all filesystem resources", "Manages the execution of application processes", "Shields programmers from the complexity of hardware", "All of the above", 3);
        addNewQuestion("Define Algorithm: ", "Something that has been pre-established with the creation of a programming language", "Some sort of program that runs a determined number of tasks in order to serve a purpose", "A number of steps to accomplish a task", "A programming paradigm that allows a better flow of information and improves readability", 3);
        addNewQuestion("(Guaranteed score for those with a sharper eye... or those that attend the middle toilet more than they should!!!\n\nOne of these is written EXACTLY like this on one of the walls: ", "public int a = \"s\";", "private String a = \"s\";", "private string s = \"S\";", "public Brighenti b = \"brigzilla\";", 1);
        addNewQuestion("Java is a sort of typed language... can you replace \"sort of\" with a better word? Do it now: ", "Runtime", "Highly", "Compiled", "Statically", 4);
        addNewQuestion("You should know by now the 4 pillars of Java, however, in this example only one of them is spelled the right way, which one?", "Capsulation", "Heritage", "Polymorfism", "Abstraction", 4);
        addNewQuestion("Do you remember the 3 interfaces that extend from Collection?", "HashMap, HashSet & Hashcode", "LinkedList, ArrayList & PriorityQueue", "List, Iterator & Set", "Queue, Set & List", 4);
        addNewQuestion("How many types of non-static inner classes do you know?", "OuterClass, InnerClass & LocalClass", "AnonymousClass, OuterClass & ProtectedClass", "PrivateClass, PublicClass & Enums", "InnerClass, AnonymousClass & LocalClass", 4);
        addNewQuestion("How many types of networks do you know? ", "NOS, TMN & MEO", "WAN, PAN & TAN", "MEO, NOS & Vodafone", "LAN, WAN & MAN", 4);
        addNewQuestion("What are the 3 errors concurrent programming \"offers\" us? ", "Visibility, Brain Damage & Mistification", "Immutability, Disordering, Subatomicity", "Atomicity, Visibility & Ordering", "Visibility, Immutability & Atomicity", 3);
        addNewQuestion("What is a design pattern?", "Finished design that can be transformed directly into code", "General repeatable solution to a commonly occurring problem", "An abstract type that is used to specify a behavior that classes must implement", "None of the above", 2);
        addNewQuestion("Who is the father of Modern Computer Science?", "Philip Don Estridge", "Steve Jobs", "Alan Turing;", "John von Neumann", 3);
        addNewQuestion("Processes can communicate through...", "Sockets", "Interfaces", "Memory", "Inter Process Communication Mechanisms", 4);
        addNewQuestion("Processes can communicate through a variety of Inter Process Communication mechanisms, such as: ", "Sockets, Interfaces, Shared Memory, Semaphores", "Interfaces, Inheritance, Sockets", "Files, Interfaces, Semaphores, Sockets", "Sockets, Signal Handler, Shared Memory, Semaphores and Files", 4);
        addNewQuestion("Which of the following options best describe the Primitive types?", "Data type that do not have any functionality other than simple data storage", "Data types that reference objects", "Data types that are not built into the Java language", "None of the above", 1);
        addNewQuestion("When should we use Wrappers over Primitive types?", "As an argument of methods that expect an object", "To use object methods (e.g. convert from/to String)", "All object contains a reference to a corresponding class object", "All of the above", 4);
        addNewQuestion("Choose the wrong answer: ", "Assignment operation is used to assign a value to a variable", "Arithmetic operators are used to perform simple arithmetic operations on primitive data types","Relational operators are used for testing multiple conditions", "Relational operators evaluate to a boolean value", 3);
        addNewQuestion("In Java, how do you declare an array with values?", "String[] awesomePeople = {\"Ventura\", \"Tiago\", \"Pedro Certo\", \"Leila\"};", "String[4] = [\"Ventura\", \"Tiago\", \"Pedro Certo\", \"Leila\"]","String[] awesomePeople = {\"Ventura\", \"Tiago\", \"Pedro Certo\", \"Leila\"}", "String[] awesomePeople = [\"Ventura\", \"Tiago\", \"Pedro Certo\", \"Leila\"];", 1);
        addNewQuestion("Alongside object oriented design, packages will help you organize your code, they are used for: ", "Avoiding name conflicts", "Hierarchical organization", "Access Control", "All of the above", 4);
        addNewQuestion("What are Markup Languages?", "Specifies code for formatting the logic within a layout", "The code used to specify the formatting are called posts and needs to comply to some sort of grammar", "Designed for the processing, definition and presentation of text", "Options A and C are correct", 3);
        addNewQuestion("Interfaces...", "Define a contract for the classes to follow", "Take advantage of the polymorphism benefits", "Limited by the inheritance rigid structure", "Options A and B are correct", 4);
        addNewQuestion("Out of the total amount of costs associated with a piece of software, the percentage dedicated to maintenance is: ","10%","85%","80%","90%",3);
        addNewQuestion("Which of the following operations is a tool for generating java documentation ?","Javadoc","Javaoc","Javadocumentation","Documentation.org",1);
        addNewQuestion("What are the three fundamental technologies that lay the foundations of WWW","HTTP, UDP, TCP" , "HTML, URL, HTTP" , "HTML, PORT, HTTP" , "HTML, URI, HTTP" , 4);
        addNewQuestion("What are the 4 verbs that define a specific action in HTTP", "FETCH, POST, PUT, DELETE" , "EAT, SLEEP, CODE, REPEAT" , "GET, POST, PUT, DELETE" , "GET, SHOW, PUT, DELETE", 3);
        addNewQuestion("What is the HTTP code that corresponds to OK?", "199","200","201","202",2);
        addNewQuestion("What are the 4 pillars of object oriented programming?", "Abstraction, Inheritance, Polymorphism, Encapsulation" , "Statically, Inheritance, Polymorphism, Encapsulation" , "Abstraction, Heritage, Poly, Encapsulation" , "None of the above" , 1);
        addNewQuestion("Choose the appropriate data type for this value: 5.5","int","double","String","boolean",2);
        addNewQuestion("Java runs on________","Windows","Linux","Mac","All of 'em",4);


    }
}
