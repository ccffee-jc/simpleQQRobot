package com.ccffee.coolQHttpServer.mbg.model;

import java.util.ArrayList;
import java.util.List;

public class CqTaskExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CqTaskExample() {
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

        public Criteria andTargeidIsNull() {
            addCriterion("targeId is null");
            return (Criteria) this;
        }

        public Criteria andTargeidIsNotNull() {
            addCriterion("targeId is not null");
            return (Criteria) this;
        }

        public Criteria andTargeidEqualTo(Integer value) {
            addCriterion("targeId =", value, "targeid");
            return (Criteria) this;
        }

        public Criteria andTargeidNotEqualTo(Integer value) {
            addCriterion("targeId <>", value, "targeid");
            return (Criteria) this;
        }

        public Criteria andTargeidGreaterThan(Integer value) {
            addCriterion("targeId >", value, "targeid");
            return (Criteria) this;
        }

        public Criteria andTargeidGreaterThanOrEqualTo(Integer value) {
            addCriterion("targeId >=", value, "targeid");
            return (Criteria) this;
        }

        public Criteria andTargeidLessThan(Integer value) {
            addCriterion("targeId <", value, "targeid");
            return (Criteria) this;
        }

        public Criteria andTargeidLessThanOrEqualTo(Integer value) {
            addCriterion("targeId <=", value, "targeid");
            return (Criteria) this;
        }

        public Criteria andTargeidIn(List<Integer> values) {
            addCriterion("targeId in", values, "targeid");
            return (Criteria) this;
        }

        public Criteria andTargeidNotIn(List<Integer> values) {
            addCriterion("targeId not in", values, "targeid");
            return (Criteria) this;
        }

        public Criteria andTargeidBetween(Integer value1, Integer value2) {
            addCriterion("targeId between", value1, value2, "targeid");
            return (Criteria) this;
        }

        public Criteria andTargeidNotBetween(Integer value1, Integer value2) {
            addCriterion("targeId not between", value1, value2, "targeid");
            return (Criteria) this;
        }

        public Criteria andTargetypeIsNull() {
            addCriterion("targeType is null");
            return (Criteria) this;
        }

        public Criteria andTargetypeIsNotNull() {
            addCriterion("targeType is not null");
            return (Criteria) this;
        }

        public Criteria andTargetypeEqualTo(String value) {
            addCriterion("targeType =", value, "targetype");
            return (Criteria) this;
        }

        public Criteria andTargetypeNotEqualTo(String value) {
            addCriterion("targeType <>", value, "targetype");
            return (Criteria) this;
        }

        public Criteria andTargetypeGreaterThan(String value) {
            addCriterion("targeType >", value, "targetype");
            return (Criteria) this;
        }

        public Criteria andTargetypeGreaterThanOrEqualTo(String value) {
            addCriterion("targeType >=", value, "targetype");
            return (Criteria) this;
        }

        public Criteria andTargetypeLessThan(String value) {
            addCriterion("targeType <", value, "targetype");
            return (Criteria) this;
        }

        public Criteria andTargetypeLessThanOrEqualTo(String value) {
            addCriterion("targeType <=", value, "targetype");
            return (Criteria) this;
        }

        public Criteria andTargetypeLike(String value) {
            addCriterion("targeType like", value, "targetype");
            return (Criteria) this;
        }

        public Criteria andTargetypeNotLike(String value) {
            addCriterion("targeType not like", value, "targetype");
            return (Criteria) this;
        }

        public Criteria andTargetypeIn(List<String> values) {
            addCriterion("targeType in", values, "targetype");
            return (Criteria) this;
        }

        public Criteria andTargetypeNotIn(List<String> values) {
            addCriterion("targeType not in", values, "targetype");
            return (Criteria) this;
        }

        public Criteria andTargetypeBetween(String value1, String value2) {
            addCriterion("targeType between", value1, value2, "targetype");
            return (Criteria) this;
        }

        public Criteria andTargetypeNotBetween(String value1, String value2) {
            addCriterion("targeType not between", value1, value2, "targetype");
            return (Criteria) this;
        }

        public Criteria andMessageIsNull() {
            addCriterion("message is null");
            return (Criteria) this;
        }

        public Criteria andMessageIsNotNull() {
            addCriterion("message is not null");
            return (Criteria) this;
        }

        public Criteria andMessageEqualTo(String value) {
            addCriterion("message =", value, "message");
            return (Criteria) this;
        }

        public Criteria andMessageNotEqualTo(String value) {
            addCriterion("message <>", value, "message");
            return (Criteria) this;
        }

        public Criteria andMessageGreaterThan(String value) {
            addCriterion("message >", value, "message");
            return (Criteria) this;
        }

        public Criteria andMessageGreaterThanOrEqualTo(String value) {
            addCriterion("message >=", value, "message");
            return (Criteria) this;
        }

        public Criteria andMessageLessThan(String value) {
            addCriterion("message <", value, "message");
            return (Criteria) this;
        }

        public Criteria andMessageLessThanOrEqualTo(String value) {
            addCriterion("message <=", value, "message");
            return (Criteria) this;
        }

        public Criteria andMessageLike(String value) {
            addCriterion("message like", value, "message");
            return (Criteria) this;
        }

        public Criteria andMessageNotLike(String value) {
            addCriterion("message not like", value, "message");
            return (Criteria) this;
        }

        public Criteria andMessageIn(List<String> values) {
            addCriterion("message in", values, "message");
            return (Criteria) this;
        }

        public Criteria andMessageNotIn(List<String> values) {
            addCriterion("message not in", values, "message");
            return (Criteria) this;
        }

        public Criteria andMessageBetween(String value1, String value2) {
            addCriterion("message between", value1, value2, "message");
            return (Criteria) this;
        }

        public Criteria andMessageNotBetween(String value1, String value2) {
            addCriterion("message not between", value1, value2, "message");
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