import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class FlashcardApp {
    private Map<String, String> flashcards;

    // コンストラクタ
    public FlashcardApp() {
        flashcards = new HashMap<>();
    }

    // 単語と意味のペアを追加する
    public void addFlashcard(String word, String meaning) {
        flashcards.put(word, meaning);
        System.out.println("単語「" + word + "」とその意味「" + meaning + "」が追加されました。");
    }

    // 単語と意味のペアを削除する
    public void removeFlashcard(String word) {
        if (flashcards.containsKey(word)) {
            flashcards.remove(word);
            System.out.println("単語「" + word + "」が削除されました。");
        } else {
            System.out.println("その単語は登録されていません。");
        }
    }

    // 単語帳を表示
    public void showFlashcards() {
        if (flashcards.isEmpty()) {
            System.out.println("登録された単語はありません。");
        } else {
            System.out.println("登録されている単語と意味:");
            for (Map.Entry<String, String> entry : flashcards.entrySet()) {
                System.out.println("単語: " + entry.getKey() + ", 意味: " + entry.getValue());
            }
        }
    }

    // 単語の意味を表示して、ユーザーに正解か不正解かを確認する
    public void testFlashcards() {
        if (flashcards.isEmpty()) {
            System.out.println("単語帳に単語が登録されていません。まず「1. 単語と意味を追加」で単語を登録してください。");
            return;  // 単語が登録されていない場合はテストを終了
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("単語の意味を表示し、正しい単語を入力してください。");

        int correctCount = 0;  // 正解数
        int totalCount = flashcards.size();  // 問題数

        // ランダムに単語とその意味を選択
        List<String> words = new ArrayList<>(flashcards.keySet());
        Collections.shuffle(words);

        for (String word : words) {
            String meaning = flashcards.get(word);
            System.out.println("意味: " + meaning);
            System.out.print("正しい単語は何ですか? ");
            String userAnswer = scanner.nextLine().trim();

            if (userAnswer.equalsIgnoreCase(word)) {
                System.out.println("正解です！\n");
                correctCount++;
            } else {
                System.out.println("不正解です。正解は「" + word + "」でした。\n");
            }
        }

        // 正解率を表示
        double accuracy = (double) correctCount / totalCount * 100;
        System.out.printf("正解率: %.2f%%\n", accuracy);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FlashcardApp app = new FlashcardApp();

        while (true) {
            System.out.println("1. 単語と意味を追加");
            System.out.println("2. 単語と意味を削除");
            System.out.println("3. 単語帳テスト");
            System.out.println("4. 登録された単語帳を表示");
            System.out.println("5. 終了");
            System.out.print("選択してください: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // バッファクリア

            switch (choice) {
                case 1:
                    System.out.print("追加する単語を入力してください: ");
                    String addWord = scanner.nextLine();
                    System.out.print("その意味を入力してください: ");
                    String addMeaning = scanner.nextLine();
                    app.addFlashcard(addWord, addMeaning);
                    break;
                case 2:
                    System.out.print("削除する単語を入力してください: ");
                    String removeWord = scanner.nextLine();
                    app.removeFlashcard(removeWord);
                    break;
                case 3:
                    app.testFlashcards();
                    break;
                case 4:
                    app.showFlashcards();
                    break;
                case 5:
                    System.out.println("終了します。");
                    return;
                default:
                    System.out.println("無効な選択肢です。もう一度選んでください。");
            }
        }
    }
}
