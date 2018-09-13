package com.holley.elecsafe.model.dat;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DatEsDetectorL1RealExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DatEsDetectorL1RealExample() {
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

        public Criteria andDetidIsNull() {
            addCriterion("DETID is null");
            return (Criteria) this;
        }

        public Criteria andDetidIsNotNull() {
            addCriterion("DETID is not null");
            return (Criteria) this;
        }

        public Criteria andDetidEqualTo(Integer value) {
            addCriterion("DETID =", value, "detid");
            return (Criteria) this;
        }

        public Criteria andDetidNotEqualTo(Integer value) {
            addCriterion("DETID <>", value, "detid");
            return (Criteria) this;
        }

        public Criteria andDetidGreaterThan(Integer value) {
            addCriterion("DETID >", value, "detid");
            return (Criteria) this;
        }

        public Criteria andDetidGreaterThanOrEqualTo(Integer value) {
            addCriterion("DETID >=", value, "detid");
            return (Criteria) this;
        }

        public Criteria andDetidLessThan(Integer value) {
            addCriterion("DETID <", value, "detid");
            return (Criteria) this;
        }

        public Criteria andDetidLessThanOrEqualTo(Integer value) {
            addCriterion("DETID <=", value, "detid");
            return (Criteria) this;
        }

        public Criteria andDetidIn(List<Integer> values) {
            addCriterion("DETID in", values, "detid");
            return (Criteria) this;
        }

        public Criteria andDetidNotIn(List<Integer> values) {
            addCriterion("DETID not in", values, "detid");
            return (Criteria) this;
        }

        public Criteria andDetidBetween(Integer value1, Integer value2) {
            addCriterion("DETID between", value1, value2, "detid");
            return (Criteria) this;
        }

        public Criteria andDetidNotBetween(Integer value1, Integer value2) {
            addCriterion("DETID not between", value1, value2, "detid");
            return (Criteria) this;
        }

        public Criteria andDatatimeIsNull() {
            addCriterion("DATATIME is null");
            return (Criteria) this;
        }

        public Criteria andDatatimeIsNotNull() {
            addCriterion("DATATIME is not null");
            return (Criteria) this;
        }

        public Criteria andDatatimeEqualTo(Date value) {
            addCriterion("DATATIME =", value, "datatime");
            return (Criteria) this;
        }

        public Criteria andDatatimeNotEqualTo(Date value) {
            addCriterion("DATATIME <>", value, "datatime");
            return (Criteria) this;
        }

        public Criteria andDatatimeGreaterThan(Date value) {
            addCriterion("DATATIME >", value, "datatime");
            return (Criteria) this;
        }

        public Criteria andDatatimeGreaterThanOrEqualTo(Date value) {
            addCriterion("DATATIME >=", value, "datatime");
            return (Criteria) this;
        }

        public Criteria andDatatimeLessThan(Date value) {
            addCriterion("DATATIME <", value, "datatime");
            return (Criteria) this;
        }

        public Criteria andDatatimeLessThanOrEqualTo(Date value) {
            addCriterion("DATATIME <=", value, "datatime");
            return (Criteria) this;
        }

        public Criteria andDatatimeIn(List<Date> values) {
            addCriterion("DATATIME in", values, "datatime");
            return (Criteria) this;
        }

        public Criteria andDatatimeNotIn(List<Date> values) {
            addCriterion("DATATIME not in", values, "datatime");
            return (Criteria) this;
        }

        public Criteria andDatatimeBetween(Date value1, Date value2) {
            addCriterion("DATATIME between", value1, value2, "datatime");
            return (Criteria) this;
        }

        public Criteria andDatatimeNotBetween(Date value1, Date value2) {
            addCriterion("DATATIME not between", value1, value2, "datatime");
            return (Criteria) this;
        }

        public Criteria andUIsNull() {
            addCriterion("U is null");
            return (Criteria) this;
        }

        public Criteria andUIsNotNull() {
            addCriterion("U is not null");
            return (Criteria) this;
        }

        public Criteria andUEqualTo(BigDecimal value) {
            addCriterion("U =", value, "u");
            return (Criteria) this;
        }

        public Criteria andUNotEqualTo(BigDecimal value) {
            addCriterion("U <>", value, "u");
            return (Criteria) this;
        }

        public Criteria andUGreaterThan(BigDecimal value) {
            addCriterion("U >", value, "u");
            return (Criteria) this;
        }

        public Criteria andUGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("U >=", value, "u");
            return (Criteria) this;
        }

        public Criteria andULessThan(BigDecimal value) {
            addCriterion("U <", value, "u");
            return (Criteria) this;
        }

        public Criteria andULessThanOrEqualTo(BigDecimal value) {
            addCriterion("U <=", value, "u");
            return (Criteria) this;
        }

        public Criteria andUIn(List<BigDecimal> values) {
            addCriterion("U in", values, "u");
            return (Criteria) this;
        }

        public Criteria andUNotIn(List<BigDecimal> values) {
            addCriterion("U not in", values, "u");
            return (Criteria) this;
        }

        public Criteria andUBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("U between", value1, value2, "u");
            return (Criteria) this;
        }

        public Criteria andUNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("U not between", value1, value2, "u");
            return (Criteria) this;
        }

        public Criteria andUaIsNull() {
            addCriterion("UA is null");
            return (Criteria) this;
        }

        public Criteria andUaIsNotNull() {
            addCriterion("UA is not null");
            return (Criteria) this;
        }

        public Criteria andUaEqualTo(BigDecimal value) {
            addCriterion("UA =", value, "ua");
            return (Criteria) this;
        }

        public Criteria andUaNotEqualTo(BigDecimal value) {
            addCriterion("UA <>", value, "ua");
            return (Criteria) this;
        }

        public Criteria andUaGreaterThan(BigDecimal value) {
            addCriterion("UA >", value, "ua");
            return (Criteria) this;
        }

        public Criteria andUaGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("UA >=", value, "ua");
            return (Criteria) this;
        }

        public Criteria andUaLessThan(BigDecimal value) {
            addCriterion("UA <", value, "ua");
            return (Criteria) this;
        }

        public Criteria andUaLessThanOrEqualTo(BigDecimal value) {
            addCriterion("UA <=", value, "ua");
            return (Criteria) this;
        }

        public Criteria andUaIn(List<BigDecimal> values) {
            addCriterion("UA in", values, "ua");
            return (Criteria) this;
        }

        public Criteria andUaNotIn(List<BigDecimal> values) {
            addCriterion("UA not in", values, "ua");
            return (Criteria) this;
        }

        public Criteria andUaBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("UA between", value1, value2, "ua");
            return (Criteria) this;
        }

        public Criteria andUaNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("UA not between", value1, value2, "ua");
            return (Criteria) this;
        }

        public Criteria andUbIsNull() {
            addCriterion("UB is null");
            return (Criteria) this;
        }

        public Criteria andUbIsNotNull() {
            addCriterion("UB is not null");
            return (Criteria) this;
        }

        public Criteria andUbEqualTo(BigDecimal value) {
            addCriterion("UB =", value, "ub");
            return (Criteria) this;
        }

        public Criteria andUbNotEqualTo(BigDecimal value) {
            addCriterion("UB <>", value, "ub");
            return (Criteria) this;
        }

        public Criteria andUbGreaterThan(BigDecimal value) {
            addCriterion("UB >", value, "ub");
            return (Criteria) this;
        }

        public Criteria andUbGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("UB >=", value, "ub");
            return (Criteria) this;
        }

        public Criteria andUbLessThan(BigDecimal value) {
            addCriterion("UB <", value, "ub");
            return (Criteria) this;
        }

        public Criteria andUbLessThanOrEqualTo(BigDecimal value) {
            addCriterion("UB <=", value, "ub");
            return (Criteria) this;
        }

        public Criteria andUbIn(List<BigDecimal> values) {
            addCriterion("UB in", values, "ub");
            return (Criteria) this;
        }

        public Criteria andUbNotIn(List<BigDecimal> values) {
            addCriterion("UB not in", values, "ub");
            return (Criteria) this;
        }

        public Criteria andUbBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("UB between", value1, value2, "ub");
            return (Criteria) this;
        }

        public Criteria andUbNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("UB not between", value1, value2, "ub");
            return (Criteria) this;
        }

        public Criteria andUcIsNull() {
            addCriterion("UC is null");
            return (Criteria) this;
        }

        public Criteria andUcIsNotNull() {
            addCriterion("UC is not null");
            return (Criteria) this;
        }

        public Criteria andUcEqualTo(BigDecimal value) {
            addCriterion("UC =", value, "uc");
            return (Criteria) this;
        }

        public Criteria andUcNotEqualTo(BigDecimal value) {
            addCriterion("UC <>", value, "uc");
            return (Criteria) this;
        }

        public Criteria andUcGreaterThan(BigDecimal value) {
            addCriterion("UC >", value, "uc");
            return (Criteria) this;
        }

        public Criteria andUcGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("UC >=", value, "uc");
            return (Criteria) this;
        }

        public Criteria andUcLessThan(BigDecimal value) {
            addCriterion("UC <", value, "uc");
            return (Criteria) this;
        }

        public Criteria andUcLessThanOrEqualTo(BigDecimal value) {
            addCriterion("UC <=", value, "uc");
            return (Criteria) this;
        }

        public Criteria andUcIn(List<BigDecimal> values) {
            addCriterion("UC in", values, "uc");
            return (Criteria) this;
        }

        public Criteria andUcNotIn(List<BigDecimal> values) {
            addCriterion("UC not in", values, "uc");
            return (Criteria) this;
        }

        public Criteria andUcBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("UC between", value1, value2, "uc");
            return (Criteria) this;
        }

        public Criteria andUcNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("UC not between", value1, value2, "uc");
            return (Criteria) this;
        }

        public Criteria andIIsNull() {
            addCriterion("I is null");
            return (Criteria) this;
        }

        public Criteria andIIsNotNull() {
            addCriterion("I is not null");
            return (Criteria) this;
        }

        public Criteria andIEqualTo(BigDecimal value) {
            addCriterion("I =", value, "i");
            return (Criteria) this;
        }

        public Criteria andINotEqualTo(BigDecimal value) {
            addCriterion("I <>", value, "i");
            return (Criteria) this;
        }

        public Criteria andIGreaterThan(BigDecimal value) {
            addCriterion("I >", value, "i");
            return (Criteria) this;
        }

        public Criteria andIGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("I >=", value, "i");
            return (Criteria) this;
        }

        public Criteria andILessThan(BigDecimal value) {
            addCriterion("I <", value, "i");
            return (Criteria) this;
        }

        public Criteria andILessThanOrEqualTo(BigDecimal value) {
            addCriterion("I <=", value, "i");
            return (Criteria) this;
        }

        public Criteria andIIn(List<BigDecimal> values) {
            addCriterion("I in", values, "i");
            return (Criteria) this;
        }

        public Criteria andINotIn(List<BigDecimal> values) {
            addCriterion("I not in", values, "i");
            return (Criteria) this;
        }

        public Criteria andIBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("I between", value1, value2, "i");
            return (Criteria) this;
        }

        public Criteria andINotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("I not between", value1, value2, "i");
            return (Criteria) this;
        }

        public Criteria andIaIsNull() {
            addCriterion("IA is null");
            return (Criteria) this;
        }

        public Criteria andIaIsNotNull() {
            addCriterion("IA is not null");
            return (Criteria) this;
        }

        public Criteria andIaEqualTo(BigDecimal value) {
            addCriterion("IA =", value, "ia");
            return (Criteria) this;
        }

        public Criteria andIaNotEqualTo(BigDecimal value) {
            addCriterion("IA <>", value, "ia");
            return (Criteria) this;
        }

        public Criteria andIaGreaterThan(BigDecimal value) {
            addCriterion("IA >", value, "ia");
            return (Criteria) this;
        }

        public Criteria andIaGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("IA >=", value, "ia");
            return (Criteria) this;
        }

        public Criteria andIaLessThan(BigDecimal value) {
            addCriterion("IA <", value, "ia");
            return (Criteria) this;
        }

        public Criteria andIaLessThanOrEqualTo(BigDecimal value) {
            addCriterion("IA <=", value, "ia");
            return (Criteria) this;
        }

        public Criteria andIaIn(List<BigDecimal> values) {
            addCriterion("IA in", values, "ia");
            return (Criteria) this;
        }

        public Criteria andIaNotIn(List<BigDecimal> values) {
            addCriterion("IA not in", values, "ia");
            return (Criteria) this;
        }

        public Criteria andIaBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("IA between", value1, value2, "ia");
            return (Criteria) this;
        }

        public Criteria andIaNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("IA not between", value1, value2, "ia");
            return (Criteria) this;
        }

        public Criteria andIbIsNull() {
            addCriterion("IB is null");
            return (Criteria) this;
        }

        public Criteria andIbIsNotNull() {
            addCriterion("IB is not null");
            return (Criteria) this;
        }

        public Criteria andIbEqualTo(BigDecimal value) {
            addCriterion("IB =", value, "ib");
            return (Criteria) this;
        }

        public Criteria andIbNotEqualTo(BigDecimal value) {
            addCriterion("IB <>", value, "ib");
            return (Criteria) this;
        }

        public Criteria andIbGreaterThan(BigDecimal value) {
            addCriterion("IB >", value, "ib");
            return (Criteria) this;
        }

        public Criteria andIbGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("IB >=", value, "ib");
            return (Criteria) this;
        }

        public Criteria andIbLessThan(BigDecimal value) {
            addCriterion("IB <", value, "ib");
            return (Criteria) this;
        }

        public Criteria andIbLessThanOrEqualTo(BigDecimal value) {
            addCriterion("IB <=", value, "ib");
            return (Criteria) this;
        }

        public Criteria andIbIn(List<BigDecimal> values) {
            addCriterion("IB in", values, "ib");
            return (Criteria) this;
        }

        public Criteria andIbNotIn(List<BigDecimal> values) {
            addCriterion("IB not in", values, "ib");
            return (Criteria) this;
        }

        public Criteria andIbBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("IB between", value1, value2, "ib");
            return (Criteria) this;
        }

        public Criteria andIbNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("IB not between", value1, value2, "ib");
            return (Criteria) this;
        }

        public Criteria andIcIsNull() {
            addCriterion("IC is null");
            return (Criteria) this;
        }

        public Criteria andIcIsNotNull() {
            addCriterion("IC is not null");
            return (Criteria) this;
        }

        public Criteria andIcEqualTo(BigDecimal value) {
            addCriterion("IC =", value, "ic");
            return (Criteria) this;
        }

        public Criteria andIcNotEqualTo(BigDecimal value) {
            addCriterion("IC <>", value, "ic");
            return (Criteria) this;
        }

        public Criteria andIcGreaterThan(BigDecimal value) {
            addCriterion("IC >", value, "ic");
            return (Criteria) this;
        }

        public Criteria andIcGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("IC >=", value, "ic");
            return (Criteria) this;
        }

        public Criteria andIcLessThan(BigDecimal value) {
            addCriterion("IC <", value, "ic");
            return (Criteria) this;
        }

        public Criteria andIcLessThanOrEqualTo(BigDecimal value) {
            addCriterion("IC <=", value, "ic");
            return (Criteria) this;
        }

        public Criteria andIcIn(List<BigDecimal> values) {
            addCriterion("IC in", values, "ic");
            return (Criteria) this;
        }

        public Criteria andIcNotIn(List<BigDecimal> values) {
            addCriterion("IC not in", values, "ic");
            return (Criteria) this;
        }

        public Criteria andIcBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("IC between", value1, value2, "ic");
            return (Criteria) this;
        }

        public Criteria andIcNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("IC not between", value1, value2, "ic");
            return (Criteria) this;
        }

        public Criteria andInxIsNull() {
            addCriterion("INX is null");
            return (Criteria) this;
        }

        public Criteria andInxIsNotNull() {
            addCriterion("INX is not null");
            return (Criteria) this;
        }

        public Criteria andInxEqualTo(BigDecimal value) {
            addCriterion("INX =", value, "inx");
            return (Criteria) this;
        }

        public Criteria andInxNotEqualTo(BigDecimal value) {
            addCriterion("INX <>", value, "inx");
            return (Criteria) this;
        }

        public Criteria andInxGreaterThan(BigDecimal value) {
            addCriterion("INX >", value, "inx");
            return (Criteria) this;
        }

        public Criteria andInxGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("INX >=", value, "inx");
            return (Criteria) this;
        }

        public Criteria andInxLessThan(BigDecimal value) {
            addCriterion("INX <", value, "inx");
            return (Criteria) this;
        }

        public Criteria andInxLessThanOrEqualTo(BigDecimal value) {
            addCriterion("INX <=", value, "inx");
            return (Criteria) this;
        }

        public Criteria andInxIn(List<BigDecimal> values) {
            addCriterion("INX in", values, "inx");
            return (Criteria) this;
        }

        public Criteria andInxNotIn(List<BigDecimal> values) {
            addCriterion("INX not in", values, "inx");
            return (Criteria) this;
        }

        public Criteria andInxBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("INX between", value1, value2, "inx");
            return (Criteria) this;
        }

        public Criteria andInxNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("INX not between", value1, value2, "inx");
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