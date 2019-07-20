package com.tradeshift.codingchallenge.repositoryapi;

public interface StructureRepository {

    String get(String id);

    void save(String model);

    void update(String model);
}
