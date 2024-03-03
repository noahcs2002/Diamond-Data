package com.dd.api.restapi.models;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Objects;

public class SqlDateWrapper {

    private int day, month, year;
    private java.sql.Date sqlDate;

    @JsonCreator
    public SqlDateWrapper(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
        this.sqlDate = new java.sql.Date(year, month, day);
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public java.sql.Date getSqlDate() {
        return sqlDate;
    }

    public void setSqlDate(java.sql.Date sqlDate) {
        this.sqlDate = sqlDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SqlDateWrapper sqlDateWrapper = (SqlDateWrapper) o;
        return day == sqlDateWrapper.day && month == sqlDateWrapper.month && year == sqlDateWrapper.year && Objects.equals(sqlDate, sqlDateWrapper.sqlDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(day, month, year, sqlDate);
    }
}
