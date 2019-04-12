package project.helpers;

import org.springframework.ui.ModelMap;

public class FlashSession {
    public static final String ERROR_TYPE = "error";
    public static final String SUCCESS_TYPE = "success";

    public static ModelMap addMessage(ModelMap modelMap, String key, String message){
        modelMap.addAttribute(key, message);
        return modelMap;
    }

    public static ModelMap addErrorMessage(ModelMap model, String message){
        return FlashSession.addMessage(model, FlashSession.ERROR_TYPE, message);
    }

    public static ModelMap addSuccessMessage(ModelMap model, String message){
        return FlashSession.addMessage(model, FlashSession.SUCCESS_TYPE, message);
    }


}
