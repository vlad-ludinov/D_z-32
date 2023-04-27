import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class task {
    public static void main(String[] args) throws Exception {
        {
            // 1.
            FileWriter fileWriter = new FileWriter("text1.txt");
            fileWriter.write("List of human:");
            fileWriter.close();
            FileReader fileReader = new FileReader("text1.txt");
            Scanner scanner = new Scanner(fileReader);
            System.out.println(scanner.nextLine());
            fileReader.close();
        }
        {
            // 3, 2.
            FileReader fileReader = new FileReader("text2.txt");
            Scanner scanner = new Scanner(fileReader);
            ArrayList<String> text = read_text(scanner);
            fileReader.close();
            ArrayList<String> surnames = new ArrayList<String>();
            ArrayList<String> names = new ArrayList<String>();
            ArrayList<String> father_names = new ArrayList<String>();
            ArrayList<Integer> ages = new ArrayList<Integer>();
            ArrayList<String> gender = new ArrayList<String>();
            filling_lists(text, surnames, names, father_names, ages, gender);
            ArrayList<Integer> indexes = get_index(ages);
            text = formating_characteristic(surnames, names, father_names, ages, gender);
            print_characteristic(indexes, text);
            System.out.println();
            // 4.
            System.out.println("List sorted by age:");
            indexes = index_sort_ages(indexes, copy_ArrayList_Integers(ages));
            print_characteristic(indexes, text);

        }
    }

    static ArrayList<String> read_text(Scanner scanner) {
        ArrayList<String> text = new ArrayList<String>();
        while (scanner.hasNextLine()) {
            text.add(scanner.nextLine());
        }
        return text;
    }

    static void filling_lists(ArrayList<String> characteristic, ArrayList<String> surnames, ArrayList<String> names,
            ArrayList<String> father_names, ArrayList<Integer> ages, ArrayList<String> gender) {
        ArrayList<String> string_human = new ArrayList<String>();
        for (String string : characteristic) {
            string_human = separation_characteristic(string);
            surnames.add(string_human.get(0));
            names.add(string_human.get(1));
            father_names.add(string_human.get(2));
            ages.add(Integer.parseInt(string_human.get(3)));
            gender.add(string_human.get(4));
        }
    }

    static ArrayList<String> separation_characteristic(String string) {
        ArrayList<String> split_string = new ArrayList<String>();
        for (String word : string.split(" ")) {
            split_string.add(word);
        }
        return split_string;
    }

    static ArrayList<String> formating_characteristic(ArrayList<String> surnames, ArrayList<String> names,
            ArrayList<String> father_names, ArrayList<Integer> ages, ArrayList<String> gender) {
        ArrayList<String> new_characteristic = new ArrayList<String>();
        for (int i = 0; i < surnames.size(); i++) {
            new_characteristic.add(surnames.get(i) + " " + names.get(i).charAt(0) + "." + father_names.get(i).charAt(0)
                    + ". " + ages.get(i) + " " + gender.get(i));
        }
        return new_characteristic;
    }

    static void print_characteristic(ArrayList<Integer> indexes, ArrayList<String> characteristic) {
        for (Integer i : indexes) {
            System.out.println(characteristic.get(i));
        }
    }

    static ArrayList<Integer> get_index(ArrayList<Integer> list) {
        ArrayList<Integer> list_index = new ArrayList<Integer>();
        for (int i = 0; i < list.size(); i++) {
            list_index.add(i);
        }
        return list_index;
    }

    static ArrayList<Integer> index_sort_ages(ArrayList<Integer> list_index, ArrayList<Integer> list) {
        int temp_list = 0;
        int temp_index = 0;
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = i; j < list.size(); j++) {
                if (list.get(j) < list.get(i)) {
                    temp_list = list.get(i);
                    temp_index = list_index.get(i);
                    list.set(i, list.get(j));
                    list_index.set(i, list_index.get(j));
                    list.set(j, temp_list);
                    list_index.set(j, temp_index);
                }
            }
        }
        return list_index;
    }

    static ArrayList<Integer> copy_ArrayList_Integers(ArrayList<Integer> list) {
        ArrayList<Integer> copy_list = new ArrayList<Integer>();
        for (int i = 0; i < list.size(); i++) {
            copy_list.add(list.get(i));
        }
        return copy_list;
    }
}
