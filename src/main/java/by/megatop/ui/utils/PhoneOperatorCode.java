package by.megatop.ui.utils;

public enum PhoneOperatorCode {
    CODE44("44"),
    CODE29("29"),
    CODE25("25"),
    CODE33("33");

    private String code;

    PhoneOperatorCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
