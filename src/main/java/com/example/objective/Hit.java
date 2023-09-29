package com.example.objective;

import jakarta.persistence.*;

import java.io.Serializable;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;


/**
 * Класс Hit представляет собой модель точки, сохраняемой в базе данных.
 */
@Table(name = "hits")
@Entity
public class Hit implements Serializable {


    /**
     * Аннотация @Id указывает, что это поле представляет собой первичный ключ (идентификатор) объекта в базе данных.
     * Поле id будет использоваться для уникальной идентификации каждой сохраненной точки.
     *
     * Аннотация @GeneratedValue(strategy = GenerationType.AUTO) указывает на автоматическую генерацию значения поля id.
     * Стратегия GenerationType.AUTO означает, что способ генерации будет определен провайдером JPA.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id = 0;

    /**
     * Колонка, представляющая значение координаты X точки.
     */
    @Column
    private double x;

    /**
     * Колонка, представляющая значение координаты Y точки.
     */
    @Column
    private double y;

    /**
     * Колонка, представляющая строковое значение координаты Y точки, используется для ввода и парсинга.
     */
    @Column
    private String yLine;

    /**
     * Колонка, представляющая значение радиуса точки (по умолчанию 2.0).
     */
    @Column
    private double r = 2d;

    /**
     * Колонка, представляющая время выполнения операции проверки точки в наносекундах.
     */
    @Column
    private long exTime;

    /**
     * Колонка, представляющая булево значение успешности проверки точки (true - Hit, false - Miss).
     */
    @Column
    private boolean success;

    /**
     * Колонка, представляющая строковое представление даты и времени сохранения точки.
     */
    @Column
    private String date;

    /**
     * Колонка, представляющая строковое представление успешности точки (Hit или Miss).
     */
    @Column
    private String successLine;


    /**
     * Получает строковое представление успешности точки (Hit или Miss).
     *
     * @return Строковое представление успешности точки.
     */
    public String getSuccessLine() {
        return successLine;
    }


    /**
     * Получает координату X точки.
     *
     * @return Значение координаты X.
     */
    public double getX() {
        return x;
    }


    /**
     * Устанавливает координату X точки.
     *
     * @param x Значение координаты X.
     */
    public void setX(double x) {
        this.x = x;
    }


    /**
     * Получает строковое представление координаты Y точки.
     *
     * @return Строковое представление координаты Y.
     */
    public String getyLine() {
        return yLine;
    }


    /**
     * Устанавливает строковое представление координаты Y точки и вычисляет соответствующее значение Y.
     *
     * @param yLine Строковое представление координаты Y.
     */
    public void setyLine(String yLine) {
        this.yLine = yLine;
        setY(Double.MIN_VALUE);
    }

    /**
     * Получает значение координаты Y точки.
     *
     * @return Значение координаты Y.
     */
    public double getY() {
        return y;
    }


    /**
     * Устанавливает значение координаты Y точки. Если Y равно Double.MIN_VALUE, то значение вычисляется из строки yLine.
     *
     * @param y Значение координаты Y.
     */
    public void setY(double y) {
        if (y == Double.MIN_VALUE) {
            try {
                this.y = Double.parseDouble(yLine);

            } catch (NumberFormatException ex) {
                this.y = Double.parseDouble(yLine.replaceAll(",", "."));
            }
        } else {
            this.y = y;
        }
    }


    /**
     * Получает радиус точки.
     *
     * @return Значение радиуса.
     */
    public double getR() {
        return r;
    }

    /**
     * Устанавливает радиус точки, приводя его к десятичной форме.
     *
     * @param r Значение радиуса.
     */
    public void setR(double r) {
        this.r = r/100;
    }


    /**
     * Получает время выполнения операции проверки точки.
     *
     * @return Время выполнения в наносекундах.
     */
    public long getExTime() {
        return exTime;
    }


    /**
     * Устанавливает время выполнения операции проверки точки.
     *
     * @param exTime Время выполнения в наносекундах.
     */
    public void setExTime(long exTime) {
        this.exTime = exTime;
    }


    /**
     * Получает результат проверки точки (true - Hit, false - Miss).
     *
     * @return Результат проверки точки.
     */
    public boolean isSuccess() {
        return success;
    }


    /**
     * Устанавливает результат проверки точки и соответствующее строковое представление.
     *
     * @param success Результат проверки точки (true - Hit, false - Miss).
     */
    public void setSuccess(boolean success) {
        this.success = success;
        this.successLine = success ? "Hit" : "Miss";
    }


    /**
     * Получает строковое представление даты и времени сохранения точки.
     *
     * @return Строковое представление даты и времени.
     */
    public String getDate() {
        return date;
    }


    /**
     * Устанавливает дату и время сохранения точки в заданном формате.
     *
     * @param date Дата и время в формате LocalDateTime.
     */
    public void setDate(LocalDateTime date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.date = date.format(formatter);
    }

}
