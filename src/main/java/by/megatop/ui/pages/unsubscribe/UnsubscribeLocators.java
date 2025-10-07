package by.megatop.ui.pages.unsubscribe;

public class UnsubscribeLocators {

    public static final String UNSUBSCRIBE_PAGE_URL = "https://megatop.by/email_unsubscribe";
    public static final String INPUT_EMAIL = "//input[@type='text']";
    public static final String BUTTON_SUBMIT = "(//button[@type='button'])[2]/span";
    public static final String TEXT_SUCCESS_MESSAGE = "//div[contains(text(), 'Вам на почту выслано письмо для отписки от рассылки')]";
    public static final String EMAIL_FORM_HEADER = "(//div[@class='d-flex flex-column']/div)[1]";
    public static final String PLACEHOLDER_INPUT_FIELD = "//div[@class='subscription__input-label']";
    public static final String UNSUBSCRIBE_CONFIRMATION_TEXT = "//div[@class='w-100 d-flex justify-center align-center']/div";

}
