package com.holley.elecsafe.model.es;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EsDetectorThresholdExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public EsDetectorThresholdExample() {
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
            addCriterion("ID is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("ID is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("ID =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("ID <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("ID >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("ID >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("ID <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("ID <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("ID in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("ID not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("ID between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("ID not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andOveruIsNull() {
            addCriterion("OVERU is null");
            return (Criteria) this;
        }

        public Criteria andOveruIsNotNull() {
            addCriterion("OVERU is not null");
            return (Criteria) this;
        }

        public Criteria andOveruEqualTo(BigDecimal value) {
            addCriterion("OVERU =", value, "overu");
            return (Criteria) this;
        }

        public Criteria andOveruNotEqualTo(BigDecimal value) {
            addCriterion("OVERU <>", value, "overu");
            return (Criteria) this;
        }

        public Criteria andOveruGreaterThan(BigDecimal value) {
            addCriterion("OVERU >", value, "overu");
            return (Criteria) this;
        }

        public Criteria andOveruGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("OVERU >=", value, "overu");
            return (Criteria) this;
        }

        public Criteria andOveruLessThan(BigDecimal value) {
            addCriterion("OVERU <", value, "overu");
            return (Criteria) this;
        }

        public Criteria andOveruLessThanOrEqualTo(BigDecimal value) {
            addCriterion("OVERU <=", value, "overu");
            return (Criteria) this;
        }

        public Criteria andOveruIn(List<BigDecimal> values) {
            addCriterion("OVERU in", values, "overu");
            return (Criteria) this;
        }

        public Criteria andOveruNotIn(List<BigDecimal> values) {
            addCriterion("OVERU not in", values, "overu");
            return (Criteria) this;
        }

        public Criteria andOveruBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("OVERU between", value1, value2, "overu");
            return (Criteria) this;
        }

        public Criteria andOveruNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("OVERU not between", value1, value2, "overu");
            return (Criteria) this;
        }

        public Criteria andUnderuIsNull() {
            addCriterion("UNDERU is null");
            return (Criteria) this;
        }

        public Criteria andUnderuIsNotNull() {
            addCriterion("UNDERU is not null");
            return (Criteria) this;
        }

        public Criteria andUnderuEqualTo(BigDecimal value) {
            addCriterion("UNDERU =", value, "underu");
            return (Criteria) this;
        }

        public Criteria andUnderuNotEqualTo(BigDecimal value) {
            addCriterion("UNDERU <>", value, "underu");
            return (Criteria) this;
        }

        public Criteria andUnderuGreaterThan(BigDecimal value) {
            addCriterion("UNDERU >", value, "underu");
            return (Criteria) this;
        }

        public Criteria andUnderuGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("UNDERU >=", value, "underu");
            return (Criteria) this;
        }

        public Criteria andUnderuLessThan(BigDecimal value) {
            addCriterion("UNDERU <", value, "underu");
            return (Criteria) this;
        }

        public Criteria andUnderuLessThanOrEqualTo(BigDecimal value) {
            addCriterion("UNDERU <=", value, "underu");
            return (Criteria) this;
        }

        public Criteria andUnderuIn(List<BigDecimal> values) {
            addCriterion("UNDERU in", values, "underu");
            return (Criteria) this;
        }

        public Criteria andUnderuNotIn(List<BigDecimal> values) {
            addCriterion("UNDERU not in", values, "underu");
            return (Criteria) this;
        }

        public Criteria andUnderuBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("UNDERU between", value1, value2, "underu");
            return (Criteria) this;
        }

        public Criteria andUnderuNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("UNDERU not between", value1, value2, "underu");
            return (Criteria) this;
        }

        public Criteria andOveriIsNull() {
            addCriterion("OVERI is null");
            return (Criteria) this;
        }

        public Criteria andOveriIsNotNull() {
            addCriterion("OVERI is not null");
            return (Criteria) this;
        }

        public Criteria andOveriEqualTo(BigDecimal value) {
            addCriterion("OVERI =", value, "overi");
            return (Criteria) this;
        }

        public Criteria andOveriNotEqualTo(BigDecimal value) {
            addCriterion("OVERI <>", value, "overi");
            return (Criteria) this;
        }

        public Criteria andOveriGreaterThan(BigDecimal value) {
            addCriterion("OVERI >", value, "overi");
            return (Criteria) this;
        }

        public Criteria andOveriGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("OVERI >=", value, "overi");
            return (Criteria) this;
        }

        public Criteria andOveriLessThan(BigDecimal value) {
            addCriterion("OVERI <", value, "overi");
            return (Criteria) this;
        }

        public Criteria andOveriLessThanOrEqualTo(BigDecimal value) {
            addCriterion("OVERI <=", value, "overi");
            return (Criteria) this;
        }

        public Criteria andOveriIn(List<BigDecimal> values) {
            addCriterion("OVERI in", values, "overi");
            return (Criteria) this;
        }

        public Criteria andOveriNotIn(List<BigDecimal> values) {
            addCriterion("OVERI not in", values, "overi");
            return (Criteria) this;
        }

        public Criteria andOveriBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("OVERI between", value1, value2, "overi");
            return (Criteria) this;
        }

        public Criteria andOveriNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("OVERI not between", value1, value2, "overi");
            return (Criteria) this;
        }

        public Criteria andIlIsNull() {
            addCriterion("IL is null");
            return (Criteria) this;
        }

        public Criteria andIlIsNotNull() {
            addCriterion("IL is not null");
            return (Criteria) this;
        }

        public Criteria andIlEqualTo(BigDecimal value) {
            addCriterion("IL =", value, "il");
            return (Criteria) this;
        }

        public Criteria andIlNotEqualTo(BigDecimal value) {
            addCriterion("IL <>", value, "il");
            return (Criteria) this;
        }

        public Criteria andIlGreaterThan(BigDecimal value) {
            addCriterion("IL >", value, "il");
            return (Criteria) this;
        }

        public Criteria andIlGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("IL >=", value, "il");
            return (Criteria) this;
        }

        public Criteria andIlLessThan(BigDecimal value) {
            addCriterion("IL <", value, "il");
            return (Criteria) this;
        }

        public Criteria andIlLessThanOrEqualTo(BigDecimal value) {
            addCriterion("IL <=", value, "il");
            return (Criteria) this;
        }

        public Criteria andIlIn(List<BigDecimal> values) {
            addCriterion("IL in", values, "il");
            return (Criteria) this;
        }

        public Criteria andIlNotIn(List<BigDecimal> values) {
            addCriterion("IL not in", values, "il");
            return (Criteria) this;
        }

        public Criteria andIlBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("IL between", value1, value2, "il");
            return (Criteria) this;
        }

        public Criteria andIlNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("IL not between", value1, value2, "il");
            return (Criteria) this;
        }

        public Criteria andTaIsNull() {
            addCriterion("TA is null");
            return (Criteria) this;
        }

        public Criteria andTaIsNotNull() {
            addCriterion("TA is not null");
            return (Criteria) this;
        }

        public Criteria andTaEqualTo(BigDecimal value) {
            addCriterion("TA =", value, "ta");
            return (Criteria) this;
        }

        public Criteria andTaNotEqualTo(BigDecimal value) {
            addCriterion("TA <>", value, "ta");
            return (Criteria) this;
        }

        public Criteria andTaGreaterThan(BigDecimal value) {
            addCriterion("TA >", value, "ta");
            return (Criteria) this;
        }

        public Criteria andTaGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("TA >=", value, "ta");
            return (Criteria) this;
        }

        public Criteria andTaLessThan(BigDecimal value) {
            addCriterion("TA <", value, "ta");
            return (Criteria) this;
        }

        public Criteria andTaLessThanOrEqualTo(BigDecimal value) {
            addCriterion("TA <=", value, "ta");
            return (Criteria) this;
        }

        public Criteria andTaIn(List<BigDecimal> values) {
            addCriterion("TA in", values, "ta");
            return (Criteria) this;
        }

        public Criteria andTaNotIn(List<BigDecimal> values) {
            addCriterion("TA not in", values, "ta");
            return (Criteria) this;
        }

        public Criteria andTaBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("TA between", value1, value2, "ta");
            return (Criteria) this;
        }

        public Criteria andTaNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("TA not between", value1, value2, "ta");
            return (Criteria) this;
        }

        public Criteria andTbIsNull() {
            addCriterion("TB is null");
            return (Criteria) this;
        }

        public Criteria andTbIsNotNull() {
            addCriterion("TB is not null");
            return (Criteria) this;
        }

        public Criteria andTbEqualTo(BigDecimal value) {
            addCriterion("TB =", value, "tb");
            return (Criteria) this;
        }

        public Criteria andTbNotEqualTo(BigDecimal value) {
            addCriterion("TB <>", value, "tb");
            return (Criteria) this;
        }

        public Criteria andTbGreaterThan(BigDecimal value) {
            addCriterion("TB >", value, "tb");
            return (Criteria) this;
        }

        public Criteria andTbGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("TB >=", value, "tb");
            return (Criteria) this;
        }

        public Criteria andTbLessThan(BigDecimal value) {
            addCriterion("TB <", value, "tb");
            return (Criteria) this;
        }

        public Criteria andTbLessThanOrEqualTo(BigDecimal value) {
            addCriterion("TB <=", value, "tb");
            return (Criteria) this;
        }

        public Criteria andTbIn(List<BigDecimal> values) {
            addCriterion("TB in", values, "tb");
            return (Criteria) this;
        }

        public Criteria andTbNotIn(List<BigDecimal> values) {
            addCriterion("TB not in", values, "tb");
            return (Criteria) this;
        }

        public Criteria andTbBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("TB between", value1, value2, "tb");
            return (Criteria) this;
        }

        public Criteria andTbNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("TB not between", value1, value2, "tb");
            return (Criteria) this;
        }

        public Criteria andTcIsNull() {
            addCriterion("TC is null");
            return (Criteria) this;
        }

        public Criteria andTcIsNotNull() {
            addCriterion("TC is not null");
            return (Criteria) this;
        }

        public Criteria andTcEqualTo(BigDecimal value) {
            addCriterion("TC =", value, "tc");
            return (Criteria) this;
        }

        public Criteria andTcNotEqualTo(BigDecimal value) {
            addCriterion("TC <>", value, "tc");
            return (Criteria) this;
        }

        public Criteria andTcGreaterThan(BigDecimal value) {
            addCriterion("TC >", value, "tc");
            return (Criteria) this;
        }

        public Criteria andTcGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("TC >=", value, "tc");
            return (Criteria) this;
        }

        public Criteria andTcLessThan(BigDecimal value) {
            addCriterion("TC <", value, "tc");
            return (Criteria) this;
        }

        public Criteria andTcLessThanOrEqualTo(BigDecimal value) {
            addCriterion("TC <=", value, "tc");
            return (Criteria) this;
        }

        public Criteria andTcIn(List<BigDecimal> values) {
            addCriterion("TC in", values, "tc");
            return (Criteria) this;
        }

        public Criteria andTcNotIn(List<BigDecimal> values) {
            addCriterion("TC not in", values, "tc");
            return (Criteria) this;
        }

        public Criteria andTcBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("TC between", value1, value2, "tc");
            return (Criteria) this;
        }

        public Criteria andTcNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("TC not between", value1, value2, "tc");
            return (Criteria) this;
        }

        public Criteria andTnIsNull() {
            addCriterion("TN is null");
            return (Criteria) this;
        }

        public Criteria andTnIsNotNull() {
            addCriterion("TN is not null");
            return (Criteria) this;
        }

        public Criteria andTnEqualTo(BigDecimal value) {
            addCriterion("TN =", value, "tn");
            return (Criteria) this;
        }

        public Criteria andTnNotEqualTo(BigDecimal value) {
            addCriterion("TN <>", value, "tn");
            return (Criteria) this;
        }

        public Criteria andTnGreaterThan(BigDecimal value) {
            addCriterion("TN >", value, "tn");
            return (Criteria) this;
        }

        public Criteria andTnGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("TN >=", value, "tn");
            return (Criteria) this;
        }

        public Criteria andTnLessThan(BigDecimal value) {
            addCriterion("TN <", value, "tn");
            return (Criteria) this;
        }

        public Criteria andTnLessThanOrEqualTo(BigDecimal value) {
            addCriterion("TN <=", value, "tn");
            return (Criteria) this;
        }

        public Criteria andTnIn(List<BigDecimal> values) {
            addCriterion("TN in", values, "tn");
            return (Criteria) this;
        }

        public Criteria andTnNotIn(List<BigDecimal> values) {
            addCriterion("TN not in", values, "tn");
            return (Criteria) this;
        }

        public Criteria andTnBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("TN between", value1, value2, "tn");
            return (Criteria) this;
        }

        public Criteria andTnNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("TN not between", value1, value2, "tn");
            return (Criteria) this;
        }

        public Criteria andTlIsNull() {
            addCriterion("TL is null");
            return (Criteria) this;
        }

        public Criteria andTlIsNotNull() {
            addCriterion("TL is not null");
            return (Criteria) this;
        }

        public Criteria andTlEqualTo(BigDecimal value) {
            addCriterion("TL =", value, "tl");
            return (Criteria) this;
        }

        public Criteria andTlNotEqualTo(BigDecimal value) {
            addCriterion("TL <>", value, "tl");
            return (Criteria) this;
        }

        public Criteria andTlGreaterThan(BigDecimal value) {
            addCriterion("TL >", value, "tl");
            return (Criteria) this;
        }

        public Criteria andTlGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("TL >=", value, "tl");
            return (Criteria) this;
        }

        public Criteria andTlLessThan(BigDecimal value) {
            addCriterion("TL <", value, "tl");
            return (Criteria) this;
        }

        public Criteria andTlLessThanOrEqualTo(BigDecimal value) {
            addCriterion("TL <=", value, "tl");
            return (Criteria) this;
        }

        public Criteria andTlIn(List<BigDecimal> values) {
            addCriterion("TL in", values, "tl");
            return (Criteria) this;
        }

        public Criteria andTlNotIn(List<BigDecimal> values) {
            addCriterion("TL not in", values, "tl");
            return (Criteria) this;
        }

        public Criteria andTlBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("TL between", value1, value2, "tl");
            return (Criteria) this;
        }

        public Criteria andTlNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("TL not between", value1, value2, "tl");
            return (Criteria) this;
        }

        public Criteria andTiIsNull() {
            addCriterion("TI is null");
            return (Criteria) this;
        }

        public Criteria andTiIsNotNull() {
            addCriterion("TI is not null");
            return (Criteria) this;
        }

        public Criteria andTiEqualTo(BigDecimal value) {
            addCriterion("TI =", value, "ti");
            return (Criteria) this;
        }

        public Criteria andTiNotEqualTo(BigDecimal value) {
            addCriterion("TI <>", value, "ti");
            return (Criteria) this;
        }

        public Criteria andTiGreaterThan(BigDecimal value) {
            addCriterion("TI >", value, "ti");
            return (Criteria) this;
        }

        public Criteria andTiGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("TI >=", value, "ti");
            return (Criteria) this;
        }

        public Criteria andTiLessThan(BigDecimal value) {
            addCriterion("TI <", value, "ti");
            return (Criteria) this;
        }

        public Criteria andTiLessThanOrEqualTo(BigDecimal value) {
            addCriterion("TI <=", value, "ti");
            return (Criteria) this;
        }

        public Criteria andTiIn(List<BigDecimal> values) {
            addCriterion("TI in", values, "ti");
            return (Criteria) this;
        }

        public Criteria andTiNotIn(List<BigDecimal> values) {
            addCriterion("TI not in", values, "ti");
            return (Criteria) this;
        }

        public Criteria andTiBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("TI between", value1, value2, "ti");
            return (Criteria) this;
        }

        public Criteria andTiNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("TI not between", value1, value2, "ti");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeIsNull() {
            addCriterion("UPDATETIME is null");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeIsNotNull() {
            addCriterion("UPDATETIME is not null");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeEqualTo(Date value) {
            addCriterion("UPDATETIME =", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeNotEqualTo(Date value) {
            addCriterion("UPDATETIME <>", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeGreaterThan(Date value) {
            addCriterion("UPDATETIME >", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("UPDATETIME >=", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeLessThan(Date value) {
            addCriterion("UPDATETIME <", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeLessThanOrEqualTo(Date value) {
            addCriterion("UPDATETIME <=", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeIn(List<Date> values) {
            addCriterion("UPDATETIME in", values, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeNotIn(List<Date> values) {
            addCriterion("UPDATETIME not in", values, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeBetween(Date value1, Date value2) {
            addCriterion("UPDATETIME between", value1, value2, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeNotBetween(Date value1, Date value2) {
            addCriterion("UPDATETIME not between", value1, value2, "updatetime");
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