package Helper;

public class CustomDialogResult {
    private static int result;
    
    public static void setResult(int result) {
        CustomDialogResult.result = result;
    }
    
    public static int getResult() {
        return result;
    }
}
