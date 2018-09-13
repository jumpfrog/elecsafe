package com.holley.elecsafe.model.dat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DatEsDetectorEventDealExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DatEsDetectorEventDealExample() {
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

        public Criteria andDealtimeIsNull() {
            addCriterion("DEALTIME is null");
            return (Criteria) this;
        }

        public Criteria andDealtimeIsNotNull() {
            addCriterion("DEALTIME is not null");
            return (Criteria) this;
        }

        public Criteria andDealtimeEqualTo(Date value) {
            addCriterion("DEALTIME =", value, "dealtime");
            return (Criteria) this;
        }

        public Criteria andDealtimeNotEqualTo(Date value) {
            addCriterion("DEALTIME <>", value, "dealtime");
            return (Criteria) this;
        }

        public Criteria andDealtimeGreaterThan(Date value) {
            addCriterion("DEALTIME >", value, "dealtime");
            return (Criteria) this;
        }

        public Criteria andDealtimeGreaterThanOrEqualTo(Date value) {
            addCriterion("DEALTIME >=", value, "dealtime");
            return (Criteria) this;
        }

        public Criteria andDealtimeLessThan(Date value) {
            addCriterion("DEALTIME <", value, "dealtime");
            return (Criteria) this;
        }

        public Criteria andDealtimeLessThanOrEqualTo(Date value) {
            addCriterion("DEALTIME <=", value, "dealtime");
            return (Criteria) this;
        }

        public Criteria andDealtimeIn(List<Date> values) {
            addCriterion("DEALTIME in", values, "dealtime");
            return (Criteria) this;
        }

        public Criteria andDealtimeNotIn(List<Date> values) {
            addCriterion("DEALTIME not in", values, "dealtime");
            return (Criteria) this;
        }

        public Criteria andDealtimeBetween(Date value1, Date value2) {
            addCriterion("DEALTIME between", value1, value2, "dealtime");
            return (Criteria) this;
        }

        public Criteria andDealtimeNotBetween(Date value1, Date value2) {
            addCriterion("DEALTIME not between", value1, value2, "dealtime");
            return (Criteria) this;
        }

        public Criteria andEvtidIsNull() {
            addCriterion("EVTID is null");
            return (Criteria) this;
        }

        public Criteria andEvtidIsNotNull() {
            addCriterion("EVTID is not null");
            return (Criteria) this;
        }

        public Criteria andEvtidEqualTo(Integer value) {
            addCriterion("EVTID =", value, "evtid");
            return (Criteria) this;
        }

        public Criteria andEvtidNotEqualTo(Integer value) {
            addCriterion("EVTID <>", value, "evtid");
            return (Criteria) this;
        }

        public Criteria andEvtidGreaterThan(Integer value) {
            addCriterion("EVTID >", value, "evtid");
            return (Criteria) this;
        }

        public Criteria andEvtidGreaterThanOrEqualTo(Integer value) {
            addCriterion("EVTID >=", value, "evtid");
            return (Criteria) this;
        }

        public Criteria andEvtidLessThan(Integer value) {
            addCriterion("EVTID <", value, "evtid");
            return (Criteria) this;
        }

        public Criteria andEvtidLessThanOrEqualTo(Integer value) {
            addCriterion("EVTID <=", value, "evtid");
            return (Criteria) this;
        }

        public Criteria andEvtidIn(List<Integer> values) {
            addCriterion("EVTID in", values, "evtid");
            return (Criteria) this;
        }

        public Criteria andEvtidNotIn(List<Integer> values) {
            addCriterion("EVTID not in", values, "evtid");
            return (Criteria) this;
        }

        public Criteria andEvtidBetween(Integer value1, Integer value2) {
            addCriterion("EVTID between", value1, value2, "evtid");
            return (Criteria) this;
        }

        public Criteria andEvtidNotBetween(Integer value1, Integer value2) {
            addCriterion("EVTID not between", value1, value2, "evtid");
            return (Criteria) this;
        }

        public Criteria andDealremarkIsNull() {
            addCriterion("DEALREMARK is null");
            return (Criteria) this;
        }

        public Criteria andDealremarkIsNotNull() {
            addCriterion("DEALREMARK is not null");
            return (Criteria) this;
        }

        public Criteria andDealremarkEqualTo(String value) {
            addCriterion("DEALREMARK =", value, "dealremark");
            return (Criteria) this;
        }

        public Criteria andDealremarkNotEqualTo(String value) {
            addCriterion("DEALREMARK <>", value, "dealremark");
            return (Criteria) this;
        }

        public Criteria andDealremarkGreaterThan(String value) {
            addCriterion("DEALREMARK >", value, "dealremark");
            return (Criteria) this;
        }

        public Criteria andDealremarkGreaterThanOrEqualTo(String value) {
            addCriterion("DEALREMARK >=", value, "dealremark");
            return (Criteria) this;
        }

        public Criteria andDealremarkLessThan(String value) {
            addCriterion("DEALREMARK <", value, "dealremark");
            return (Criteria) this;
        }

        public Criteria andDealremarkLessThanOrEqualTo(String value) {
            addCriterion("DEALREMARK <=", value, "dealremark");
            return (Criteria) this;
        }

        public Criteria andDealremarkLike(String value) {
            addCriterion("DEALREMARK like", value, "dealremark");
            return (Criteria) this;
        }

        public Criteria andDealremarkNotLike(String value) {
            addCriterion("DEALREMARK not like", value, "dealremark");
            return (Criteria) this;
        }

        public Criteria andDealremarkIn(List<String> values) {
            addCriterion("DEALREMARK in", values, "dealremark");
            return (Criteria) this;
        }

        public Criteria andDealremarkNotIn(List<String> values) {
            addCriterion("DEALREMARK not in", values, "dealremark");
            return (Criteria) this;
        }

        public Criteria andDealremarkBetween(String value1, String value2) {
            addCriterion("DEALREMARK between", value1, value2, "dealremark");
            return (Criteria) this;
        }

        public Criteria andDealremarkNotBetween(String value1, String value2) {
            addCriterion("DEALREMARK not between", value1, value2, "dealremark");
            return (Criteria) this;
        }

        public Criteria andDealaccountIsNull() {
            addCriterion("DEALACCOUNT is null");
            return (Criteria) this;
        }

        public Criteria andDealaccountIsNotNull() {
            addCriterion("DEALACCOUNT is not null");
            return (Criteria) this;
        }

        public Criteria andDealaccountEqualTo(String value) {
            addCriterion("DEALACCOUNT =", value, "dealaccount");
            return (Criteria) this;
        }

        public Criteria andDealaccountNotEqualTo(String value) {
            addCriterion("DEALACCOUNT <>", value, "dealaccount");
            return (Criteria) this;
        }

        public Criteria andDealaccountGreaterThan(String value) {
            addCriterion("DEALACCOUNT >", value, "dealaccount");
            return (Criteria) this;
        }

        public Criteria andDealaccountGreaterThanOrEqualTo(String value) {
            addCriterion("DEALACCOUNT >=", value, "dealaccount");
            return (Criteria) this;
        }

        public Criteria andDealaccountLessThan(String value) {
            addCriterion("DEALACCOUNT <", value, "dealaccount");
            return (Criteria) this;
        }

        public Criteria andDealaccountLessThanOrEqualTo(String value) {
            addCriterion("DEALACCOUNT <=", value, "dealaccount");
            return (Criteria) this;
        }

        public Criteria andDealaccountLike(String value) {
            addCriterion("DEALACCOUNT like", value, "dealaccount");
            return (Criteria) this;
        }

        public Criteria andDealaccountNotLike(String value) {
            addCriterion("DEALACCOUNT not like", value, "dealaccount");
            return (Criteria) this;
        }

        public Criteria andDealaccountIn(List<String> values) {
            addCriterion("DEALACCOUNT in", values, "dealaccount");
            return (Criteria) this;
        }

        public Criteria andDealaccountNotIn(List<String> values) {
            addCriterion("DEALACCOUNT not in", values, "dealaccount");
            return (Criteria) this;
        }

        public Criteria andDealaccountBetween(String value1, String value2) {
            addCriterion("DEALACCOUNT between", value1, value2, "dealaccount");
            return (Criteria) this;
        }

        public Criteria andDealaccountNotBetween(String value1, String value2) {
            addCriterion("DEALACCOUNT not between", value1, value2, "dealaccount");
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