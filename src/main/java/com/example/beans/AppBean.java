package com.example.beans;
import com.example.database.HitService;
import com.example.objective.Hit;
import com.example.verify.Verifier;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@ManagedBean(name="appBean")
@SessionScoped
public class AppBean implements Serializable {
    /**
     * Объект класса Verifier, используемый для проверки координат точек.
     * Создается при создании экземпляра класса AppBean и используется для проверки точек перед их сохранением.
     */
    private final Verifier verifier = new Verifier();
    /**
     * Объект класса HitService, обеспечивающий взаимодействие с базой данных для сохранения и получения точек.
     * Создается при создании экземпляра класса AppBean и используется для работы с базой данных точек.
     */
    private final HitService hitService = new HitService();

    /**
     * Переменная, представляющая текущую добавляемую точку.
     * Инициализируется пустым объектом Hit при создании экземпляра класса AppBean.
     */
    private Hit hit = new Hit();
    /**
     * Список, содержащий все сохраненные точки.
     * Инициализируется данными из базы данных при создании экземпляра класса AppBean.
     */
    private List<Hit> hits = hitService.getAll();


    /**
     * Получить текущую добавляемую точку.
     *
     */
    public void add() {
        long start = System.nanoTime();
        boolean success = verifier.verifyHit(hit.getX(), hit.getY(), hit.getR());
        hit.setSuccess(success);
        hit.setDate(LocalDateTime.now());
        hit.setExTime(System.nanoTime() - start);
        hitService.add(hit);
        hits.add(hit);
        hit = new Hit();
    }

    /**
     * Установить текущую добавляемую точку.
     * @return hit
     */
    public Hit getHit() {
        return hit;
    }


    /**
     * Установить текущую добавляемую точку.
     *
     * @param hit Объект Hit, который нужно установить в качестве текущей добавляемой точки.
     */
    public void setHit(Hit hit) {
        this.hit = hit;
    }

    /**
     * Получить список всех сохраненных точек.
     *
     * @return Список объектов Hit, представляющих сохраненные точки.
     */
    public List<Hit> getHitList() {
        return hits;
    }

    /**
     * Установить список сохраненных точек.
     *
     * @param hits Список объектов Hit, который нужно установить как список сохраненных точек.
     */
    public void setHitList(List<Hit> hits) {
        this.hits = hits;
    }

    /**
     * Метод очищает базу данных и список сохраненных точек, удаляя все данные о точках.
     */
    public void clear() {
        hitService.clear();
        this.hits.clear();
    }

}

