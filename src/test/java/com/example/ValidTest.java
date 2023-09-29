package com.example;

import static org.junit.Assert.*;

import com.example.validators.Valid;
import org.junit.Test;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

public class ValidTest {

    private Valid validator = new Valid();
    private FacesContext context = FacesContext.getCurrentInstance();
    private UIComponent component = null;

    @Test
    public void testValidInput() {
        try {
            validator.validate(context, component, "3.0");
        } catch (ValidatorException e) {
            fail("Ожидался валидный ввод.");
        }
    }

    @Test
    public void testValidInputComma() {
        try {
            validator.validate(context, component, "3,0");
        } catch (ValidatorException e) {
            fail("Ожидался валидный ввод.");
        }
    }


    @Test
    public void testInvalidInput() {
        try {
            validator.validate(context, component, "10.0");
            fail("Ожидался невалидный ввод.");
        } catch (ValidatorException e) {

        }
    }

    @Test
    public void testInvalidFormat() {
        try {
            validator.validate(context, component, "abc");
            fail("Ожидался неверный формат ввода.");
        } catch (ValidatorException e) {
            // Если валидация выбросила исключение, то тест успешный
        }
    }
}