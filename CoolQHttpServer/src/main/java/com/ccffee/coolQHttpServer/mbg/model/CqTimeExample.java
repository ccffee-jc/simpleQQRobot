package com.ccffee.coolQHttpServer.mbg.model;

import java.util.ArrayList;
import java.util.List;

public class CqTimeExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CqTimeExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andTimedayIsNull() {
            addCriterion("timeDay is null");
            return (Criteria) this;
        }

        public Criteria andTimedayIsNotNull() {
            addCriterion("timeDay is not null");
            return (Criteria) this;
        }

        public Criteria andTimedayEqualTo(String value) {
            addCriterion("timeDay =", value, "timeday");
            return (Criteria) this;
        }

        public Criteria andTimedayNotEqualTo(String value) {
            addCriterion("timeDay <>", value, "timeday");
            return (Criteria) this;
        }

        public Criteria andTimedayGreaterThan(String value) {
            addCriterion("timeDay >", value, "timeday");
            return (Criteria) this;
        }

        public Criteria andTimedayGreaterThanOrEqualTo(String value) {
            addCriterion("timeDay >=", value, "timeday");
            return (Criteria) this;
        }

        public Criteria andTimedayLessThan(String value) {
            addCriterion("timeDay <", value, "timeday");
            return (Criteria) this;
        }

        public Criteria andTimedayLessThanOrEqualTo(String value) {
            addCriterion("timeDay <=", value, "timeday");
            return (Criteria) this;
        }

        public Criteria andTimedayLike(String value) {
            addCriterion("timeDay like", value, "timeday");
            return (Criteria) this;
        }

        public Criteria andTimedayNotLike(String value) {
            addCriterion("timeDay not like", value, "timeday");
            return (Criteria) this;
        }

        public Criteria andTimedayIn(List<String> values) {
            addCriterion("timeDay in", values, "timeday");
            return (Criteria) this;
        }

        public Criteria andTimedayNotIn(List<String> values) {
            addCriterion("timeDay not in", values, "timeday");
            return (Criteria) this;
        }

        public Criteria andTimedayBetween(String value1, String value2) {
            addCriterion("timeDay between", value1, value2, "timeday");
            return (Criteria) this;
        }

        public Criteria andTimedayNotBetween(String value1, String value2) {
            addCriterion("timeDay not between", value1, value2, "timeday");
            return (Criteria) this;
        }

        public Criteria andTimehourIsNull() {
            addCriterion("timeHour is null");
            return (Criteria) this;
        }

        public Criteria andTimehourIsNotNull() {
            addCriterion("timeHour is not null");
            return (Criteria) this;
        }

        public Criteria andTimehourEqualTo(String value) {
            addCriterion("timeHour =", value, "timehour");
            return (Criteria) this;
        }

        public Criteria andTimehourNotEqualTo(String value) {
            addCriterion("timeHour <>", value, "timehour");
            return (Criteria) this;
        }

        public Criteria andTimehourGreaterThan(String value) {
            addCriterion("timeHour >", value, "timehour");
            return (Criteria) this;
        }

        public Criteria andTimehourGreaterThanOrEqualTo(String value) {
            addCriterion("timeHour >=", value, "timehour");
            return (Criteria) this;
        }

        public Criteria andTimehourLessThan(String value) {
            addCriterion("timeHour <", value, "timehour");
            return (Criteria) this;
        }

        public Criteria andTimehourLessThanOrEqualTo(String value) {
            addCriterion("timeHour <=", value, "timehour");
            return (Criteria) this;
        }

        public Criteria andTimehourLike(String value) {
            addCriterion("timeHour like", value, "timehour");
            return (Criteria) this;
        }

        public Criteria andTimehourNotLike(String value) {
            addCriterion("timeHour not like", value, "timehour");
            return (Criteria) this;
        }

        public Criteria andTimehourIn(List<String> values) {
            addCriterion("timeHour in", values, "timehour");
            return (Criteria) this;
        }

        public Criteria andTimehourNotIn(List<String> values) {
            addCriterion("timeHour not in", values, "timehour");
            return (Criteria) this;
        }

        public Criteria andTimehourBetween(String value1, String value2) {
            addCriterion("timeHour between", value1, value2, "timehour");
            return (Criteria) this;
        }

        public Criteria andTimehourNotBetween(String value1, String value2) {
            addCriterion("timeHour not between", value1, value2, "timehour");
            return (Criteria) this;
        }

        public Criteria andTaskidIsNull() {
            addCriterion("taskId is null");
            return (Criteria) this;
        }

        public Criteria andTaskidIsNotNull() {
            addCriterion("taskId is not null");
            return (Criteria) this;
        }

        public Criteria andTaskidEqualTo(Integer value) {
            addCriterion("taskId =", value, "taskid");
            return (Criteria) this;
        }

        public Criteria andTaskidNotEqualTo(Integer value) {
            addCriterion("taskId <>", value, "taskid");
            return (Criteria) this;
        }

        public Criteria andTaskidGreaterThan(Integer value) {
            addCriterion("taskId >", value, "taskid");
            return (Criteria) this;
        }

        public Criteria andTaskidGreaterThanOrEqualTo(Integer value) {
            addCriterion("taskId >=", value, "taskid");
            return (Criteria) this;
        }

        public Criteria andTaskidLessThan(Integer value) {
            addCriterion("taskId <", value, "taskid");
            return (Criteria) this;
        }

        public Criteria andTaskidLessThanOrEqualTo(Integer value) {
            addCriterion("taskId <=", value, "taskid");
            return (Criteria) this;
        }

        public Criteria andTaskidIn(List<Integer> values) {
            addCriterion("taskId in", values, "taskid");
            return (Criteria) this;
        }

        public Criteria andTaskidNotIn(List<Integer> values) {
            addCriterion("taskId not in", values, "taskid");
            return (Criteria) this;
        }

        public Criteria andTaskidBetween(Integer value1, Integer value2) {
            addCriterion("taskId between", value1, value2, "taskid");
            return (Criteria) this;
        }

        public Criteria andTaskidNotBetween(Integer value1, Integer value2) {
            addCriterion("taskId not between", value1, value2, "taskid");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}