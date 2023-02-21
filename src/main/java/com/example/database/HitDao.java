package com.example.database;

import com.example.objective.Hit;

import java.util.List;

public interface HitDao {

    void add(Hit hit);

    List<Hit> getAll();

    void clear();
}
