package com.example.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.util.Map;


/**
 * Валидатор для поля ввода "y", который проверяет, что значение находится в заданном диапазоне.
 */
@FacesValidator("Validator")
public class Valid implements Validator {

    private final int MIN = -3;
    private final int MAX = 5;


    /**
     * Метод для валидации значения "y".
     *
     * @param facesContext Контекст FacesContext.
     * @param uiComponent  Компонент UIComponent, с которым связан валидатор.
     * @param o            Значение, которое требуется валидировать.
     * @throws ValidatorException Если значение "y" находится вне заданного диапазона
     *                            или возникает ошибка при преобразовании.
     */
    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {
        try {
            // Преобразование введенного значения в тип double, предварительно заменяя "," на "."
            double y = Double.parseDouble(o.toString().replaceAll(",", "."));
            // Проверка, находится ли значение в допустимом диапазоне
            if (y < MIN || y > MAX) throw new NumberFormatException();
        } catch (Exception ex) {
            // Если произошла ошибка валидации, создаем сообщение об ошибке
            throw new ValidatorException(new FacesMessage("y: double [" + MIN + "; " + MAX + "]"));
        }
    }
}
