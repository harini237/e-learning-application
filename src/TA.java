import java.sql.Connection;
import java.util.Scanner;

public class TA {
    Connection con;
    Scanner scanner = new Scanner(System.in);
    Helper helper;
    String user = "";
    String pwd = "";

    public TA(Connection conn) {
        this.con = conn;
        this.helper = new Helper(this.con);

        boolean valid = false;

        do {
            //get ta credentials and validate
            String[] creds = helper.getCredentials();
            String user = creds[0];
            String pwd = creds[1];

            if(this.user.isEmpty() || this.pwd.isEmpty())
                valid = helper.validateCredentials(user, pwd);
            else
                valid = true;

            System.out.println("Welcome TA!");
            System.out.println("1. Sign In\n2. Go Back\nEnter your choice (1-2): ");
            int choice = scanner.nextInt();

            switch(choice) {
                case 1:
                    if(valid) {
                        System.out.println("Sign in successful.");
                        this.user = user;
                        this.pwd = pwd;
                        this.landing();
                    } else {
                        System.out.println("Login incorrect, try again.");
                    }
                    break;
                case 2:
                    new Home(this.con);
                    break;
                default:
                    System.out.println("Invalid entry, exiting application.");
                    System.exit(0);
                    break;
            }
        } while(!valid);
    }

    //function for landing page
    private void landing() {
        System.out.println("1. Go to active courses\n2. View courses\n3. Change password\n4. Logout");
        System.out.println("Enter your choice (1-4): ");
        int choice = scanner.nextInt();

        switch(choice) {
            case 1:
                //redirect to active courses page
                this.goToActiveCourses();
                break;
            case 2:
                //redirect to view courses page
                this.viewCourses();
                break;
            case 3:
                //redirect to change pwd page
                this.changePassword();
                break;
            case 4:
                //redirect to home page
                new Home(this.con);
                break;
            default:
                System.out.println("Invalid entry, exiting application.");
                System.exit(0);
                break;
        }
    }

    //function to go to active courses
    private void goToActiveCourses() {
        System.out.println("Enter course ID: ");
        String courseID = scanner.nextLine();

        System.out.println("1. View students\n2. Add new chapter\n3. Modify chapters\n4. Go back");
        System.out.println("Enter your choice (1-4): ");
        int choice = scanner.nextInt();

        switch(choice) {
            case 1:
                //redirect to view students
                this.viewStudents(courseID);
                break;
            case 2:
                //redirect to add chapter
                this.addChapter(courseID);
                break;
            case 3:
                //redirect to modify chapter
                this.modifyChapter(courseID);
                break;
            case 4:
                //redirect to landing page
                this.landing();
                break;
            default:
                System.out.println("Invalid entry, exiting application.");
                System.exit(0);
                break;
        }
    }

    //function to view courses
    public void viewCourses() {
        //TODO: fetch and display courses
        System.out.println("1. Go Back");
        System.out.println("Enter your choice (1): ");
        int choice = scanner.nextInt();

        if (choice == 1) {
            this.landing();
        } else {
            System.out.println("Invalid entry, exiting application.");
            System.exit(0);
        }
    }

    //function to change password
    public void changePassword() {
        System.out.println("Enter current password: ");
        String currentPwd = scanner.nextLine();
        System.out.println("Enter new password: ");
        String newPwd = scanner.nextLine();
        System.out.println("Confirm new password: ");
        String confirmPwd = scanner.nextLine();

        System.out.println("1. Update\n2. Go Back");
        System.out.println("Enter your choice (1-2): ");
        int choice = scanner.nextInt();

        switch(choice) {
            case 1:
                if(newPwd.equals(confirmPwd)) {
                    //TODO: check if current pwd is valid
                    //TODO: handle pwd update
                } else {
                    System.out.println("Passwords don't match, try again.");
                    this.landing();
                }
                break;
            case 2:
                //redirect to landing page
                this.landing();
                break;
            default:
                System.out.println("Invalid entry, exiting application.");
                System.exit(0);
                break;
        }
    }

    //function to view students
    private void viewStudents(String courseID) {
        //TODO: fetch and display students
        System.out.println("1. Go Back");
        System.out.println("Enter your choice (1): ");
        int choice = scanner.nextInt();

        if (choice == 1) {
            this.landing();
        } else {
            System.out.println("Invalid entry, exiting application.");
            System.exit(0);
        }
    }

    //function to add chapter
    private void addChapter(String courseID) {
        System.out.println("Enter unique chapter ID: ");
        String chapterID = scanner.nextLine();
        System.out.println("Enter chapter title: ");
        String chapterTitle = scanner.nextLine();

        System.out.println("1. Add new section\n2. Go back");
        System.out.println("Enter your choice (1-2): ");
        int choice = scanner.nextInt();

        switch(choice) {
            case 1:
                //redirect to add section
                System.out.println("Success.");
                this.addSection("add", courseID, chapterID, chapterTitle);
                break;
            case 2:
                //redirect to previous page
                this.goToActiveCourses();
                break;
            default:
                System.out.println("Invalid entry, exiting application.");
                System.exit(0);
                break;
        }
    }

    //function to modify chapter
    private void modifyChapter(String courseID) {
        System.out.println("Enter unique chapter ID: ");
        String chapterID = scanner.nextLine();

        System.out.println("1. Add new section\n2. Add new activity\n3. Go back");
        System.out.println("Enter your choice (1-3): ");
        int choice = scanner.nextInt();

        switch(choice) {
            case 1:
                //redirect to add section
                System.out.println("Success.");
                this.addSection("modify", courseID, chapterID, "");
                break;
            case 2:
                //redirect to add activity
                break;
            case 3:
                //redirect to previous page
                this.goToActiveCourses();
                break;
            default:
                System.out.println("Invalid entry, exiting application.");
                System.exit(0);
                break;
        }
    }

    //function to add section
    private void addSection(String callingFunction, String courseID, String chapterID, String chapterTitle) {
        System.out.println("Enter section number: ");
        String sectionNum = scanner.nextLine();
        System.out.println("Enter section title: ");
        String sectionTitle = scanner.nextLine();

        System.out.println("1. Add new content block\n2. Go back");
        System.out.println("Enter your choice (1-2): ");
        int choice = scanner.nextInt();

        switch(choice) {
            case 1:
                //redirect to add content block
                break;
            case 2:
                //redirect to previous page
                if(callingFunction.equals("add")) {
                    this.addChapter(courseID);
                } else {
                    this.modifyChapter(courseID);
                }
                break;
            default:
                System.out.println("Invalid entry, exiting application.");
                System.exit(0);
                break;
        }
    }
}
