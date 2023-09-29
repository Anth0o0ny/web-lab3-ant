package com.example.database;

import com.example.objective.Hit;

import java.util.List;

/**
 * Интерфейс HitDao представляет собой контракт для доступа к операциям с данными о точках в базе данных.
 */
public interface HitDao {

    /**
     * Добавляет точку в базу данных.
     *
     * @param hit Объект Hit, представляющий точку, которую необходимо добавить в базу данных.
     */
    void add(Hit hit);



    /**
     * Получает список всех точек из базы данных.
     *
     * @return Список объектов Hit, представляющих все сохраненные точки.
     */
    List<Hit> getAll();


    /**
     * Очищает базу данных, удаляя все данные о точках.
     */
    void clear();
}
