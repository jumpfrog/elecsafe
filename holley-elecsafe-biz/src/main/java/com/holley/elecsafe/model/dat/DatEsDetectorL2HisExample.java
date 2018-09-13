package com.holley.elecsafe.model.dat;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DatEsDetectorL2HisExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DatEsDetectorL2HisExample() {
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

        public Criteria andFIsNull() {
            addCriterion("F is null");
            return (Criteria) this;
        }

        public Criteria andFIsNotNull() {
            addCriterion("F is not null");
            return (Criteria) this;
        }

        public Criteria andFEqualTo(BigDecimal value) {
            addCriterion("F =", value, "f");
            return (Criteria) this;
        }

        public Criteria andFNotEqualTo(BigDecimal value) {
            addCriterion("F <>", value, "f");
            return (Criteria) this;
        }

        public Criteria andFGreaterThan(BigDecimal value) {
            addCriterion("F >", value, "f");
            return (Criteria) this;
        }

        public Criteria andFGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("F >=", value, "f");
            return (Criteria) this;
        }

        public Criteria andFLessThan(BigDecimal value) {
            addCriterion("F <", value, "f");
            return (Criteria) this;
        }

        public Criteria andFLessThanOrEqualTo(BigDecimal value) {
            addCriterion("F <=", value, "f");
            return (Criteria) this;
        }

        public Criteria andFIn(List<BigDecimal> values) {
            addCriterion("F in", values, "f");
            return (Criteria) this;
        }

        public Criteria andFNotIn(List<BigDecimal> values) {
            addCriterion("F not in", values, "f");
            return (Criteria) this;
        }

        public Criteria andFBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("F between", value1, value2, "f");
            return (Criteria) this;
        }

        public Criteria andFNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("F not between", value1, value2, "f");
            return (Criteria) this;
        }

        public Criteria andFaIsNull() {
            addCriterion("FA is null");
            return (Criteria) this;
        }

        public Criteria andFaIsNotNull() {
            addCriterion("FA is not null");
            return (Criteria) this;
        }

        public Criteria andFaEqualTo(BigDecimal value) {
            addCriterion("FA =", value, "fa");
            return (Criteria) this;
        }

        public Criteria andFaNotEqualTo(BigDecimal value) {
            addCriterion("FA <>", value, "fa");
            return (Criteria) this;
        }

        public Criteria andFaGreaterThan(BigDecimal value) {
            addCriterion("FA >", value, "fa");
            return (Criteria) this;
        }

        public Criteria andFaGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("FA >=", value, "fa");
            return (Criteria) this;
        }

        public Criteria andFaLessThan(BigDecimal value) {
            addCriterion("FA <", value, "fa");
            return (Criteria) this;
        }

        public Criteria andFaLessThanOrEqualTo(BigDecimal value) {
            addCriterion("FA <=", value, "fa");
            return (Criteria) this;
        }

        public Criteria andFaIn(List<BigDecimal> values) {
            addCriterion("FA in", values, "fa");
            return (Criteria) this;
        }

        public Criteria andFaNotIn(List<BigDecimal> values) {
            addCriterion("FA not in", values, "fa");
            return (Criteria) this;
        }

        public Criteria andFaBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("FA between", value1, value2, "fa");
            return (Criteria) this;
        }

        public Criteria andFaNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("FA not between", value1, value2, "fa");
            return (Criteria) this;
        }

        public Criteria andFbIsNull() {
            addCriterion("FB is null");
            return (Criteria) this;
        }

        public Criteria andFbIsNotNull() {
            addCriterion("FB is not null");
            return (Criteria) this;
        }

        public Criteria andFbEqualTo(BigDecimal value) {
            addCriterion("FB =", value, "fb");
            return (Criteria) this;
        }

        public Criteria andFbNotEqualTo(BigDecimal value) {
            addCriterion("FB <>", value, "fb");
            return (Criteria) this;
        }

        public Criteria andFbGreaterThan(BigDecimal value) {
            addCriterion("FB >", value, "fb");
            return (Criteria) this;
        }

        public Criteria andFbGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("FB >=", value, "fb");
            return (Criteria) this;
        }

        public Criteria andFbLessThan(BigDecimal value) {
            addCriterion("FB <", value, "fb");
            return (Criteria) this;
        }

        public Criteria andFbLessThanOrEqualTo(BigDecimal value) {
            addCriterion("FB <=", value, "fb");
            return (Criteria) this;
        }

        public Criteria andFbIn(List<BigDecimal> values) {
            addCriterion("FB in", values, "fb");
            return (Criteria) this;
        }

        public Criteria andFbNotIn(List<BigDecimal> values) {
            addCriterion("FB not in", values, "fb");
            return (Criteria) this;
        }

        public Criteria andFbBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("FB between", value1, value2, "fb");
            return (Criteria) this;
        }

        public Criteria andFbNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("FB not between", value1, value2, "fb");
            return (Criteria) this;
        }

        public Criteria andFcIsNull() {
            addCriterion("FC is null");
            return (Criteria) this;
        }

        public Criteria andFcIsNotNull() {
            addCriterion("FC is not null");
            return (Criteria) this;
        }

        public Criteria andFcEqualTo(BigDecimal value) {
            addCriterion("FC =", value, "fc");
            return (Criteria) this;
        }

        public Criteria andFcNotEqualTo(BigDecimal value) {
            addCriterion("FC <>", value, "fc");
            return (Criteria) this;
        }

        public Criteria andFcGreaterThan(BigDecimal value) {
            addCriterion("FC >", value, "fc");
            return (Criteria) this;
        }

        public Criteria andFcGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("FC >=", value, "fc");
            return (Criteria) this;
        }

        public Criteria andFcLessThan(BigDecimal value) {
            addCriterion("FC <", value, "fc");
            return (Criteria) this;
        }

        public Criteria andFcLessThanOrEqualTo(BigDecimal value) {
            addCriterion("FC <=", value, "fc");
            return (Criteria) this;
        }

        public Criteria andFcIn(List<BigDecimal> values) {
            addCriterion("FC in", values, "fc");
            return (Criteria) this;
        }

        public Criteria andFcNotIn(List<BigDecimal> values) {
            addCriterion("FC not in", values, "fc");
            return (Criteria) this;
        }

        public Criteria andFcBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("FC between", value1, value2, "fc");
            return (Criteria) this;
        }

        public Criteria andFcNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("FC not between", value1, value2, "fc");
            return (Criteria) this;
        }

        public Criteria andPIsNull() {
            addCriterion("P is null");
            return (Criteria) this;
        }

        public Criteria andPIsNotNull() {
            addCriterion("P is not null");
            return (Criteria) this;
        }

        public Criteria andPEqualTo(BigDecimal value) {
            addCriterion("P =", value, "p");
            return (Criteria) this;
        }

        public Criteria andPNotEqualTo(BigDecimal value) {
            addCriterion("P <>", value, "p");
            return (Criteria) this;
        }

        public Criteria andPGreaterThan(BigDecimal value) {
            addCriterion("P >", value, "p");
            return (Criteria) this;
        }

        public Criteria andPGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("P >=", value, "p");
            return (Criteria) this;
        }

        public Criteria andPLessThan(BigDecimal value) {
            addCriterion("P <", value, "p");
            return (Criteria) this;
        }

        public Criteria andPLessThanOrEqualTo(BigDecimal value) {
            addCriterion("P <=", value, "p");
            return (Criteria) this;
        }

        public Criteria andPIn(List<BigDecimal> values) {
            addCriterion("P in", values, "p");
            return (Criteria) this;
        }

        public Criteria andPNotIn(List<BigDecimal> values) {
            addCriterion("P not in", values, "p");
            return (Criteria) this;
        }

        public Criteria andPBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("P between", value1, value2, "p");
            return (Criteria) this;
        }

        public Criteria andPNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("P not between", value1, value2, "p");
            return (Criteria) this;
        }

        public Criteria andPaIsNull() {
            addCriterion("PA is null");
            return (Criteria) this;
        }

        public Criteria andPaIsNotNull() {
            addCriterion("PA is not null");
            return (Criteria) this;
        }

        public Criteria andPaEqualTo(BigDecimal value) {
            addCriterion("PA =", value, "pa");
            return (Criteria) this;
        }

        public Criteria andPaNotEqualTo(BigDecimal value) {
            addCriterion("PA <>", value, "pa");
            return (Criteria) this;
        }

        public Criteria andPaGreaterThan(BigDecimal value) {
            addCriterion("PA >", value, "pa");
            return (Criteria) this;
        }

        public Criteria andPaGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("PA >=", value, "pa");
            return (Criteria) this;
        }

        public Criteria andPaLessThan(BigDecimal value) {
            addCriterion("PA <", value, "pa");
            return (Criteria) this;
        }

        public Criteria andPaLessThanOrEqualTo(BigDecimal value) {
            addCriterion("PA <=", value, "pa");
            return (Criteria) this;
        }

        public Criteria andPaIn(List<BigDecimal> values) {
            addCriterion("PA in", values, "pa");
            return (Criteria) this;
        }

        public Criteria andPaNotIn(List<BigDecimal> values) {
            addCriterion("PA not in", values, "pa");
            return (Criteria) this;
        }

        public Criteria andPaBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("PA between", value1, value2, "pa");
            return (Criteria) this;
        }

        public Criteria andPaNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("PA not between", value1, value2, "pa");
            return (Criteria) this;
        }

        public Criteria andPbIsNull() {
            addCriterion("PB is null");
            return (Criteria) this;
        }

        public Criteria andPbIsNotNull() {
            addCriterion("PB is not null");
            return (Criteria) this;
        }

        public Criteria andPbEqualTo(BigDecimal value) {
            addCriterion("PB =", value, "pb");
            return (Criteria) this;
        }

        public Criteria andPbNotEqualTo(BigDecimal value) {
            addCriterion("PB <>", value, "pb");
            return (Criteria) this;
        }

        public Criteria andPbGreaterThan(BigDecimal value) {
            addCriterion("PB >", value, "pb");
            return (Criteria) this;
        }

        public Criteria andPbGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("PB >=", value, "pb");
            return (Criteria) this;
        }

        public Criteria andPbLessThan(BigDecimal value) {
            addCriterion("PB <", value, "pb");
            return (Criteria) this;
        }

        public Criteria andPbLessThanOrEqualTo(BigDecimal value) {
            addCriterion("PB <=", value, "pb");
            return (Criteria) this;
        }

        public Criteria andPbIn(List<BigDecimal> values) {
            addCriterion("PB in", values, "pb");
            return (Criteria) this;
        }

        public Criteria andPbNotIn(List<BigDecimal> values) {
            addCriterion("PB not in", values, "pb");
            return (Criteria) this;
        }

        public Criteria andPbBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("PB between", value1, value2, "pb");
            return (Criteria) this;
        }

        public Criteria andPbNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("PB not between", value1, value2, "pb");
            return (Criteria) this;
        }

        public Criteria andPcIsNull() {
            addCriterion("PC is null");
            return (Criteria) this;
        }

        public Criteria andPcIsNotNull() {
            addCriterion("PC is not null");
            return (Criteria) this;
        }

        public Criteria andPcEqualTo(BigDecimal value) {
            addCriterion("PC =", value, "pc");
            return (Criteria) this;
        }

        public Criteria andPcNotEqualTo(BigDecimal value) {
            addCriterion("PC <>", value, "pc");
            return (Criteria) this;
        }

        public Criteria andPcGreaterThan(BigDecimal value) {
            addCriterion("PC >", value, "pc");
            return (Criteria) this;
        }

        public Criteria andPcGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("PC >=", value, "pc");
            return (Criteria) this;
        }

        public Criteria andPcLessThan(BigDecimal value) {
            addCriterion("PC <", value, "pc");
            return (Criteria) this;
        }

        public Criteria andPcLessThanOrEqualTo(BigDecimal value) {
            addCriterion("PC <=", value, "pc");
            return (Criteria) this;
        }

        public Criteria andPcIn(List<BigDecimal> values) {
            addCriterion("PC in", values, "pc");
            return (Criteria) this;
        }

        public Criteria andPcNotIn(List<BigDecimal> values) {
            addCriterion("PC not in", values, "pc");
            return (Criteria) this;
        }

        public Criteria andPcBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("PC between", value1, value2, "pc");
            return (Criteria) this;
        }

        public Criteria andPcNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("PC not between", value1, value2, "pc");
            return (Criteria) this;
        }

        public Criteria andQIsNull() {
            addCriterion("Q is null");
            return (Criteria) this;
        }

        public Criteria andQIsNotNull() {
            addCriterion("Q is not null");
            return (Criteria) this;
        }

        public Criteria andQEqualTo(BigDecimal value) {
            addCriterion("Q =", value, "q");
            return (Criteria) this;
        }

        public Criteria andQNotEqualTo(BigDecimal value) {
            addCriterion("Q <>", value, "q");
            return (Criteria) this;
        }

        public Criteria andQGreaterThan(BigDecimal value) {
            addCriterion("Q >", value, "q");
            return (Criteria) this;
        }

        public Criteria andQGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("Q >=", value, "q");
            return (Criteria) this;
        }

        public Criteria andQLessThan(BigDecimal value) {
            addCriterion("Q <", value, "q");
            return (Criteria) this;
        }

        public Criteria andQLessThanOrEqualTo(BigDecimal value) {
            addCriterion("Q <=", value, "q");
            return (Criteria) this;
        }

        public Criteria andQIn(List<BigDecimal> values) {
            addCriterion("Q in", values, "q");
            return (Criteria) this;
        }

        public Criteria andQNotIn(List<BigDecimal> values) {
            addCriterion("Q not in", values, "q");
            return (Criteria) this;
        }

        public Criteria andQBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("Q between", value1, value2, "q");
            return (Criteria) this;
        }

        public Criteria andQNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("Q not between", value1, value2, "q");
            return (Criteria) this;
        }

        public Criteria andQaIsNull() {
            addCriterion("QA is null");
            return (Criteria) this;
        }

        public Criteria andQaIsNotNull() {
            addCriterion("QA is not null");
            return (Criteria) this;
        }

        public Criteria andQaEqualTo(BigDecimal value) {
            addCriterion("QA =", value, "qa");
            return (Criteria) this;
        }

        public Criteria andQaNotEqualTo(BigDecimal value) {
            addCriterion("QA <>", value, "qa");
            return (Criteria) this;
        }

        public Criteria andQaGreaterThan(BigDecimal value) {
            addCriterion("QA >", value, "qa");
            return (Criteria) this;
        }

        public Criteria andQaGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("QA >=", value, "qa");
            return (Criteria) this;
        }

        public Criteria andQaLessThan(BigDecimal value) {
            addCriterion("QA <", value, "qa");
            return (Criteria) this;
        }

        public Criteria andQaLessThanOrEqualTo(BigDecimal value) {
            addCriterion("QA <=", value, "qa");
            return (Criteria) this;
        }

        public Criteria andQaIn(List<BigDecimal> values) {
            addCriterion("QA in", values, "qa");
            return (Criteria) this;
        }

        public Criteria andQaNotIn(List<BigDecimal> values) {
            addCriterion("QA not in", values, "qa");
            return (Criteria) this;
        }

        public Criteria andQaBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("QA between", value1, value2, "qa");
            return (Criteria) this;
        }

        public Criteria andQaNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("QA not between", value1, value2, "qa");
            return (Criteria) this;
        }

        public Criteria andQbIsNull() {
            addCriterion("QB is null");
            return (Criteria) this;
        }

        public Criteria andQbIsNotNull() {
            addCriterion("QB is not null");
            return (Criteria) this;
        }

        public Criteria andQbEqualTo(BigDecimal value) {
            addCriterion("QB =", value, "qb");
            return (Criteria) this;
        }

        public Criteria andQbNotEqualTo(BigDecimal value) {
            addCriterion("QB <>", value, "qb");
            return (Criteria) this;
        }

        public Criteria andQbGreaterThan(BigDecimal value) {
            addCriterion("QB >", value, "qb");
            return (Criteria) this;
        }

        public Criteria andQbGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("QB >=", value, "qb");
            return (Criteria) this;
        }

        public Criteria andQbLessThan(BigDecimal value) {
            addCriterion("QB <", value, "qb");
            return (Criteria) this;
        }

        public Criteria andQbLessThanOrEqualTo(BigDecimal value) {
            addCriterion("QB <=", value, "qb");
            return (Criteria) this;
        }

        public Criteria andQbIn(List<BigDecimal> values) {
            addCriterion("QB in", values, "qb");
            return (Criteria) this;
        }

        public Criteria andQbNotIn(List<BigDecimal> values) {
            addCriterion("QB not in", values, "qb");
            return (Criteria) this;
        }

        public Criteria andQbBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("QB between", value1, value2, "qb");
            return (Criteria) this;
        }

        public Criteria andQbNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("QB not between", value1, value2, "qb");
            return (Criteria) this;
        }

        public Criteria andQcIsNull() {
            addCriterion("QC is null");
            return (Criteria) this;
        }

        public Criteria andQcIsNotNull() {
            addCriterion("QC is not null");
            return (Criteria) this;
        }

        public Criteria andQcEqualTo(BigDecimal value) {
            addCriterion("QC =", value, "qc");
            return (Criteria) this;
        }

        public Criteria andQcNotEqualTo(BigDecimal value) {
            addCriterion("QC <>", value, "qc");
            return (Criteria) this;
        }

        public Criteria andQcGreaterThan(BigDecimal value) {
            addCriterion("QC >", value, "qc");
            return (Criteria) this;
        }

        public Criteria andQcGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("QC >=", value, "qc");
            return (Criteria) this;
        }

        public Criteria andQcLessThan(BigDecimal value) {
            addCriterion("QC <", value, "qc");
            return (Criteria) this;
        }

        public Criteria andQcLessThanOrEqualTo(BigDecimal value) {
            addCriterion("QC <=", value, "qc");
            return (Criteria) this;
        }

        public Criteria andQcIn(List<BigDecimal> values) {
            addCriterion("QC in", values, "qc");
            return (Criteria) this;
        }

        public Criteria andQcNotIn(List<BigDecimal> values) {
            addCriterion("QC not in", values, "qc");
            return (Criteria) this;
        }

        public Criteria andQcBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("QC between", value1, value2, "qc");
            return (Criteria) this;
        }

        public Criteria andQcNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("QC not between", value1, value2, "qc");
            return (Criteria) this;
        }

        public Criteria andApIsNull() {
            addCriterion("AP is null");
            return (Criteria) this;
        }

        public Criteria andApIsNotNull() {
            addCriterion("AP is not null");
            return (Criteria) this;
        }

        public Criteria andApEqualTo(BigDecimal value) {
            addCriterion("AP =", value, "ap");
            return (Criteria) this;
        }

        public Criteria andApNotEqualTo(BigDecimal value) {
            addCriterion("AP <>", value, "ap");
            return (Criteria) this;
        }

        public Criteria andApGreaterThan(BigDecimal value) {
            addCriterion("AP >", value, "ap");
            return (Criteria) this;
        }

        public Criteria andApGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("AP >=", value, "ap");
            return (Criteria) this;
        }

        public Criteria andApLessThan(BigDecimal value) {
            addCriterion("AP <", value, "ap");
            return (Criteria) this;
        }

        public Criteria andApLessThanOrEqualTo(BigDecimal value) {
            addCriterion("AP <=", value, "ap");
            return (Criteria) this;
        }

        public Criteria andApIn(List<BigDecimal> values) {
            addCriterion("AP in", values, "ap");
            return (Criteria) this;
        }

        public Criteria andApNotIn(List<BigDecimal> values) {
            addCriterion("AP not in", values, "ap");
            return (Criteria) this;
        }

        public Criteria andApBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("AP between", value1, value2, "ap");
            return (Criteria) this;
        }

        public Criteria andApNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("AP not between", value1, value2, "ap");
            return (Criteria) this;
        }

        public Criteria andApaIsNull() {
            addCriterion("APA is null");
            return (Criteria) this;
        }

        public Criteria andApaIsNotNull() {
            addCriterion("APA is not null");
            return (Criteria) this;
        }

        public Criteria andApaEqualTo(BigDecimal value) {
            addCriterion("APA =", value, "apa");
            return (Criteria) this;
        }

        public Criteria andApaNotEqualTo(BigDecimal value) {
            addCriterion("APA <>", value, "apa");
            return (Criteria) this;
        }

        public Criteria andApaGreaterThan(BigDecimal value) {
            addCriterion("APA >", value, "apa");
            return (Criteria) this;
        }

        public Criteria andApaGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("APA >=", value, "apa");
            return (Criteria) this;
        }

        public Criteria andApaLessThan(BigDecimal value) {
            addCriterion("APA <", value, "apa");
            return (Criteria) this;
        }

        public Criteria andApaLessThanOrEqualTo(BigDecimal value) {
            addCriterion("APA <=", value, "apa");
            return (Criteria) this;
        }

        public Criteria andApaIn(List<BigDecimal> values) {
            addCriterion("APA in", values, "apa");
            return (Criteria) this;
        }

        public Criteria andApaNotIn(List<BigDecimal> values) {
            addCriterion("APA not in", values, "apa");
            return (Criteria) this;
        }

        public Criteria andApaBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("APA between", value1, value2, "apa");
            return (Criteria) this;
        }

        public Criteria andApaNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("APA not between", value1, value2, "apa");
            return (Criteria) this;
        }

        public Criteria andApbIsNull() {
            addCriterion("APB is null");
            return (Criteria) this;
        }

        public Criteria andApbIsNotNull() {
            addCriterion("APB is not null");
            return (Criteria) this;
        }

        public Criteria andApbEqualTo(BigDecimal value) {
            addCriterion("APB =", value, "apb");
            return (Criteria) this;
        }

        public Criteria andApbNotEqualTo(BigDecimal value) {
            addCriterion("APB <>", value, "apb");
            return (Criteria) this;
        }

        public Criteria andApbGreaterThan(BigDecimal value) {
            addCriterion("APB >", value, "apb");
            return (Criteria) this;
        }

        public Criteria andApbGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("APB >=", value, "apb");
            return (Criteria) this;
        }

        public Criteria andApbLessThan(BigDecimal value) {
            addCriterion("APB <", value, "apb");
            return (Criteria) this;
        }

        public Criteria andApbLessThanOrEqualTo(BigDecimal value) {
            addCriterion("APB <=", value, "apb");
            return (Criteria) this;
        }

        public Criteria andApbIn(List<BigDecimal> values) {
            addCriterion("APB in", values, "apb");
            return (Criteria) this;
        }

        public Criteria andApbNotIn(List<BigDecimal> values) {
            addCriterion("APB not in", values, "apb");
            return (Criteria) this;
        }

        public Criteria andApbBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("APB between", value1, value2, "apb");
            return (Criteria) this;
        }

        public Criteria andApbNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("APB not between", value1, value2, "apb");
            return (Criteria) this;
        }

        public Criteria andApcIsNull() {
            addCriterion("APC is null");
            return (Criteria) this;
        }

        public Criteria andApcIsNotNull() {
            addCriterion("APC is not null");
            return (Criteria) this;
        }

        public Criteria andApcEqualTo(BigDecimal value) {
            addCriterion("APC =", value, "apc");
            return (Criteria) this;
        }

        public Criteria andApcNotEqualTo(BigDecimal value) {
            addCriterion("APC <>", value, "apc");
            return (Criteria) this;
        }

        public Criteria andApcGreaterThan(BigDecimal value) {
            addCriterion("APC >", value, "apc");
            return (Criteria) this;
        }

        public Criteria andApcGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("APC >=", value, "apc");
            return (Criteria) this;
        }

        public Criteria andApcLessThan(BigDecimal value) {
            addCriterion("APC <", value, "apc");
            return (Criteria) this;
        }

        public Criteria andApcLessThanOrEqualTo(BigDecimal value) {
            addCriterion("APC <=", value, "apc");
            return (Criteria) this;
        }

        public Criteria andApcIn(List<BigDecimal> values) {
            addCriterion("APC in", values, "apc");
            return (Criteria) this;
        }

        public Criteria andApcNotIn(List<BigDecimal> values) {
            addCriterion("APC not in", values, "apc");
            return (Criteria) this;
        }

        public Criteria andApcBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("APC between", value1, value2, "apc");
            return (Criteria) this;
        }

        public Criteria andApcNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("APC not between", value1, value2, "apc");
            return (Criteria) this;
        }

        public Criteria andPfIsNull() {
            addCriterion("PF is null");
            return (Criteria) this;
        }

        public Criteria andPfIsNotNull() {
            addCriterion("PF is not null");
            return (Criteria) this;
        }

        public Criteria andPfEqualTo(BigDecimal value) {
            addCriterion("PF =", value, "pf");
            return (Criteria) this;
        }

        public Criteria andPfNotEqualTo(BigDecimal value) {
            addCriterion("PF <>", value, "pf");
            return (Criteria) this;
        }

        public Criteria andPfGreaterThan(BigDecimal value) {
            addCriterion("PF >", value, "pf");
            return (Criteria) this;
        }

        public Criteria andPfGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("PF >=", value, "pf");
            return (Criteria) this;
        }

        public Criteria andPfLessThan(BigDecimal value) {
            addCriterion("PF <", value, "pf");
            return (Criteria) this;
        }

        public Criteria andPfLessThanOrEqualTo(BigDecimal value) {
            addCriterion("PF <=", value, "pf");
            return (Criteria) this;
        }

        public Criteria andPfIn(List<BigDecimal> values) {
            addCriterion("PF in", values, "pf");
            return (Criteria) this;
        }

        public Criteria andPfNotIn(List<BigDecimal> values) {
            addCriterion("PF not in", values, "pf");
            return (Criteria) this;
        }

        public Criteria andPfBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("PF between", value1, value2, "pf");
            return (Criteria) this;
        }

        public Criteria andPfNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("PF not between", value1, value2, "pf");
            return (Criteria) this;
        }

        public Criteria andPfaIsNull() {
            addCriterion("PFA is null");
            return (Criteria) this;
        }

        public Criteria andPfaIsNotNull() {
            addCriterion("PFA is not null");
            return (Criteria) this;
        }

        public Criteria andPfaEqualTo(BigDecimal value) {
            addCriterion("PFA =", value, "pfa");
            return (Criteria) this;
        }

        public Criteria andPfaNotEqualTo(BigDecimal value) {
            addCriterion("PFA <>", value, "pfa");
            return (Criteria) this;
        }

        public Criteria andPfaGreaterThan(BigDecimal value) {
            addCriterion("PFA >", value, "pfa");
            return (Criteria) this;
        }

        public Criteria andPfaGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("PFA >=", value, "pfa");
            return (Criteria) this;
        }

        public Criteria andPfaLessThan(BigDecimal value) {
            addCriterion("PFA <", value, "pfa");
            return (Criteria) this;
        }

        public Criteria andPfaLessThanOrEqualTo(BigDecimal value) {
            addCriterion("PFA <=", value, "pfa");
            return (Criteria) this;
        }

        public Criteria andPfaIn(List<BigDecimal> values) {
            addCriterion("PFA in", values, "pfa");
            return (Criteria) this;
        }

        public Criteria andPfaNotIn(List<BigDecimal> values) {
            addCriterion("PFA not in", values, "pfa");
            return (Criteria) this;
        }

        public Criteria andPfaBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("PFA between", value1, value2, "pfa");
            return (Criteria) this;
        }

        public Criteria andPfaNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("PFA not between", value1, value2, "pfa");
            return (Criteria) this;
        }

        public Criteria andPfbIsNull() {
            addCriterion("PFB is null");
            return (Criteria) this;
        }

        public Criteria andPfbIsNotNull() {
            addCriterion("PFB is not null");
            return (Criteria) this;
        }

        public Criteria andPfbEqualTo(BigDecimal value) {
            addCriterion("PFB =", value, "pfb");
            return (Criteria) this;
        }

        public Criteria andPfbNotEqualTo(BigDecimal value) {
            addCriterion("PFB <>", value, "pfb");
            return (Criteria) this;
        }

        public Criteria andPfbGreaterThan(BigDecimal value) {
            addCriterion("PFB >", value, "pfb");
            return (Criteria) this;
        }

        public Criteria andPfbGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("PFB >=", value, "pfb");
            return (Criteria) this;
        }

        public Criteria andPfbLessThan(BigDecimal value) {
            addCriterion("PFB <", value, "pfb");
            return (Criteria) this;
        }

        public Criteria andPfbLessThanOrEqualTo(BigDecimal value) {
            addCriterion("PFB <=", value, "pfb");
            return (Criteria) this;
        }

        public Criteria andPfbIn(List<BigDecimal> values) {
            addCriterion("PFB in", values, "pfb");
            return (Criteria) this;
        }

        public Criteria andPfbNotIn(List<BigDecimal> values) {
            addCriterion("PFB not in", values, "pfb");
            return (Criteria) this;
        }

        public Criteria andPfbBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("PFB between", value1, value2, "pfb");
            return (Criteria) this;
        }

        public Criteria andPfbNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("PFB not between", value1, value2, "pfb");
            return (Criteria) this;
        }

        public Criteria andPfcIsNull() {
            addCriterion("PFC is null");
            return (Criteria) this;
        }

        public Criteria andPfcIsNotNull() {
            addCriterion("PFC is not null");
            return (Criteria) this;
        }

        public Criteria andPfcEqualTo(BigDecimal value) {
            addCriterion("PFC =", value, "pfc");
            return (Criteria) this;
        }

        public Criteria andPfcNotEqualTo(BigDecimal value) {
            addCriterion("PFC <>", value, "pfc");
            return (Criteria) this;
        }

        public Criteria andPfcGreaterThan(BigDecimal value) {
            addCriterion("PFC >", value, "pfc");
            return (Criteria) this;
        }

        public Criteria andPfcGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("PFC >=", value, "pfc");
            return (Criteria) this;
        }

        public Criteria andPfcLessThan(BigDecimal value) {
            addCriterion("PFC <", value, "pfc");
            return (Criteria) this;
        }

        public Criteria andPfcLessThanOrEqualTo(BigDecimal value) {
            addCriterion("PFC <=", value, "pfc");
            return (Criteria) this;
        }

        public Criteria andPfcIn(List<BigDecimal> values) {
            addCriterion("PFC in", values, "pfc");
            return (Criteria) this;
        }

        public Criteria andPfcNotIn(List<BigDecimal> values) {
            addCriterion("PFC not in", values, "pfc");
            return (Criteria) this;
        }

        public Criteria andPfcBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("PFC between", value1, value2, "pfc");
            return (Criteria) this;
        }

        public Criteria andPfcNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("PFC not between", value1, value2, "pfc");
            return (Criteria) this;
        }

        public Criteria andEnergypIsNull() {
            addCriterion("ENERGYP is null");
            return (Criteria) this;
        }

        public Criteria andEnergypIsNotNull() {
            addCriterion("ENERGYP is not null");
            return (Criteria) this;
        }

        public Criteria andEnergypEqualTo(BigDecimal value) {
            addCriterion("ENERGYP =", value, "energyp");
            return (Criteria) this;
        }

        public Criteria andEnergypNotEqualTo(BigDecimal value) {
            addCriterion("ENERGYP <>", value, "energyp");
            return (Criteria) this;
        }

        public Criteria andEnergypGreaterThan(BigDecimal value) {
            addCriterion("ENERGYP >", value, "energyp");
            return (Criteria) this;
        }

        public Criteria andEnergypGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("ENERGYP >=", value, "energyp");
            return (Criteria) this;
        }

        public Criteria andEnergypLessThan(BigDecimal value) {
            addCriterion("ENERGYP <", value, "energyp");
            return (Criteria) this;
        }

        public Criteria andEnergypLessThanOrEqualTo(BigDecimal value) {
            addCriterion("ENERGYP <=", value, "energyp");
            return (Criteria) this;
        }

        public Criteria andEnergypIn(List<BigDecimal> values) {
            addCriterion("ENERGYP in", values, "energyp");
            return (Criteria) this;
        }

        public Criteria andEnergypNotIn(List<BigDecimal> values) {
            addCriterion("ENERGYP not in", values, "energyp");
            return (Criteria) this;
        }

        public Criteria andEnergypBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ENERGYP between", value1, value2, "energyp");
            return (Criteria) this;
        }

        public Criteria andEnergypNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ENERGYP not between", value1, value2, "energyp");
            return (Criteria) this;
        }

        public Criteria andEnergypaIsNull() {
            addCriterion("ENERGYPA is null");
            return (Criteria) this;
        }

        public Criteria andEnergypaIsNotNull() {
            addCriterion("ENERGYPA is not null");
            return (Criteria) this;
        }

        public Criteria andEnergypaEqualTo(BigDecimal value) {
            addCriterion("ENERGYPA =", value, "energypa");
            return (Criteria) this;
        }

        public Criteria andEnergypaNotEqualTo(BigDecimal value) {
            addCriterion("ENERGYPA <>", value, "energypa");
            return (Criteria) this;
        }

        public Criteria andEnergypaGreaterThan(BigDecimal value) {
            addCriterion("ENERGYPA >", value, "energypa");
            return (Criteria) this;
        }

        public Criteria andEnergypaGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("ENERGYPA >=", value, "energypa");
            return (Criteria) this;
        }

        public Criteria andEnergypaLessThan(BigDecimal value) {
            addCriterion("ENERGYPA <", value, "energypa");
            return (Criteria) this;
        }

        public Criteria andEnergypaLessThanOrEqualTo(BigDecimal value) {
            addCriterion("ENERGYPA <=", value, "energypa");
            return (Criteria) this;
        }

        public Criteria andEnergypaIn(List<BigDecimal> values) {
            addCriterion("ENERGYPA in", values, "energypa");
            return (Criteria) this;
        }

        public Criteria andEnergypaNotIn(List<BigDecimal> values) {
            addCriterion("ENERGYPA not in", values, "energypa");
            return (Criteria) this;
        }

        public Criteria andEnergypaBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ENERGYPA between", value1, value2, "energypa");
            return (Criteria) this;
        }

        public Criteria andEnergypaNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ENERGYPA not between", value1, value2, "energypa");
            return (Criteria) this;
        }

        public Criteria andEnergypbIsNull() {
            addCriterion("ENERGYPB is null");
            return (Criteria) this;
        }

        public Criteria andEnergypbIsNotNull() {
            addCriterion("ENERGYPB is not null");
            return (Criteria) this;
        }

        public Criteria andEnergypbEqualTo(BigDecimal value) {
            addCriterion("ENERGYPB =", value, "energypb");
            return (Criteria) this;
        }

        public Criteria andEnergypbNotEqualTo(BigDecimal value) {
            addCriterion("ENERGYPB <>", value, "energypb");
            return (Criteria) this;
        }

        public Criteria andEnergypbGreaterThan(BigDecimal value) {
            addCriterion("ENERGYPB >", value, "energypb");
            return (Criteria) this;
        }

        public Criteria andEnergypbGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("ENERGYPB >=", value, "energypb");
            return (Criteria) this;
        }

        public Criteria andEnergypbLessThan(BigDecimal value) {
            addCriterion("ENERGYPB <", value, "energypb");
            return (Criteria) this;
        }

        public Criteria andEnergypbLessThanOrEqualTo(BigDecimal value) {
            addCriterion("ENERGYPB <=", value, "energypb");
            return (Criteria) this;
        }

        public Criteria andEnergypbIn(List<BigDecimal> values) {
            addCriterion("ENERGYPB in", values, "energypb");
            return (Criteria) this;
        }

        public Criteria andEnergypbNotIn(List<BigDecimal> values) {
            addCriterion("ENERGYPB not in", values, "energypb");
            return (Criteria) this;
        }

        public Criteria andEnergypbBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ENERGYPB between", value1, value2, "energypb");
            return (Criteria) this;
        }

        public Criteria andEnergypbNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ENERGYPB not between", value1, value2, "energypb");
            return (Criteria) this;
        }

        public Criteria andEnergypcIsNull() {
            addCriterion("ENERGYPC is null");
            return (Criteria) this;
        }

        public Criteria andEnergypcIsNotNull() {
            addCriterion("ENERGYPC is not null");
            return (Criteria) this;
        }

        public Criteria andEnergypcEqualTo(BigDecimal value) {
            addCriterion("ENERGYPC =", value, "energypc");
            return (Criteria) this;
        }

        public Criteria andEnergypcNotEqualTo(BigDecimal value) {
            addCriterion("ENERGYPC <>", value, "energypc");
            return (Criteria) this;
        }

        public Criteria andEnergypcGreaterThan(BigDecimal value) {
            addCriterion("ENERGYPC >", value, "energypc");
            return (Criteria) this;
        }

        public Criteria andEnergypcGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("ENERGYPC >=", value, "energypc");
            return (Criteria) this;
        }

        public Criteria andEnergypcLessThan(BigDecimal value) {
            addCriterion("ENERGYPC <", value, "energypc");
            return (Criteria) this;
        }

        public Criteria andEnergypcLessThanOrEqualTo(BigDecimal value) {
            addCriterion("ENERGYPC <=", value, "energypc");
            return (Criteria) this;
        }

        public Criteria andEnergypcIn(List<BigDecimal> values) {
            addCriterion("ENERGYPC in", values, "energypc");
            return (Criteria) this;
        }

        public Criteria andEnergypcNotIn(List<BigDecimal> values) {
            addCriterion("ENERGYPC not in", values, "energypc");
            return (Criteria) this;
        }

        public Criteria andEnergypcBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ENERGYPC between", value1, value2, "energypc");
            return (Criteria) this;
        }

        public Criteria andEnergypcNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ENERGYPC not between", value1, value2, "energypc");
            return (Criteria) this;
        }

        public Criteria andEnergyqIsNull() {
            addCriterion("ENERGYQ is null");
            return (Criteria) this;
        }

        public Criteria andEnergyqIsNotNull() {
            addCriterion("ENERGYQ is not null");
            return (Criteria) this;
        }

        public Criteria andEnergyqEqualTo(BigDecimal value) {
            addCriterion("ENERGYQ =", value, "energyq");
            return (Criteria) this;
        }

        public Criteria andEnergyqNotEqualTo(BigDecimal value) {
            addCriterion("ENERGYQ <>", value, "energyq");
            return (Criteria) this;
        }

        public Criteria andEnergyqGreaterThan(BigDecimal value) {
            addCriterion("ENERGYQ >", value, "energyq");
            return (Criteria) this;
        }

        public Criteria andEnergyqGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("ENERGYQ >=", value, "energyq");
            return (Criteria) this;
        }

        public Criteria andEnergyqLessThan(BigDecimal value) {
            addCriterion("ENERGYQ <", value, "energyq");
            return (Criteria) this;
        }

        public Criteria andEnergyqLessThanOrEqualTo(BigDecimal value) {
            addCriterion("ENERGYQ <=", value, "energyq");
            return (Criteria) this;
        }

        public Criteria andEnergyqIn(List<BigDecimal> values) {
            addCriterion("ENERGYQ in", values, "energyq");
            return (Criteria) this;
        }

        public Criteria andEnergyqNotIn(List<BigDecimal> values) {
            addCriterion("ENERGYQ not in", values, "energyq");
            return (Criteria) this;
        }

        public Criteria andEnergyqBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ENERGYQ between", value1, value2, "energyq");
            return (Criteria) this;
        }

        public Criteria andEnergyqNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ENERGYQ not between", value1, value2, "energyq");
            return (Criteria) this;
        }

        public Criteria andEnergyqaIsNull() {
            addCriterion("ENERGYQA is null");
            return (Criteria) this;
        }

        public Criteria andEnergyqaIsNotNull() {
            addCriterion("ENERGYQA is not null");
            return (Criteria) this;
        }

        public Criteria andEnergyqaEqualTo(BigDecimal value) {
            addCriterion("ENERGYQA =", value, "energyqa");
            return (Criteria) this;
        }

        public Criteria andEnergyqaNotEqualTo(BigDecimal value) {
            addCriterion("ENERGYQA <>", value, "energyqa");
            return (Criteria) this;
        }

        public Criteria andEnergyqaGreaterThan(BigDecimal value) {
            addCriterion("ENERGYQA >", value, "energyqa");
            return (Criteria) this;
        }

        public Criteria andEnergyqaGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("ENERGYQA >=", value, "energyqa");
            return (Criteria) this;
        }

        public Criteria andEnergyqaLessThan(BigDecimal value) {
            addCriterion("ENERGYQA <", value, "energyqa");
            return (Criteria) this;
        }

        public Criteria andEnergyqaLessThanOrEqualTo(BigDecimal value) {
            addCriterion("ENERGYQA <=", value, "energyqa");
            return (Criteria) this;
        }

        public Criteria andEnergyqaIn(List<BigDecimal> values) {
            addCriterion("ENERGYQA in", values, "energyqa");
            return (Criteria) this;
        }

        public Criteria andEnergyqaNotIn(List<BigDecimal> values) {
            addCriterion("ENERGYQA not in", values, "energyqa");
            return (Criteria) this;
        }

        public Criteria andEnergyqaBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ENERGYQA between", value1, value2, "energyqa");
            return (Criteria) this;
        }

        public Criteria andEnergyqaNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ENERGYQA not between", value1, value2, "energyqa");
            return (Criteria) this;
        }

        public Criteria andEnergyqbIsNull() {
            addCriterion("ENERGYQB is null");
            return (Criteria) this;
        }

        public Criteria andEnergyqbIsNotNull() {
            addCriterion("ENERGYQB is not null");
            return (Criteria) this;
        }

        public Criteria andEnergyqbEqualTo(BigDecimal value) {
            addCriterion("ENERGYQB =", value, "energyqb");
            return (Criteria) this;
        }

        public Criteria andEnergyqbNotEqualTo(BigDecimal value) {
            addCriterion("ENERGYQB <>", value, "energyqb");
            return (Criteria) this;
        }

        public Criteria andEnergyqbGreaterThan(BigDecimal value) {
            addCriterion("ENERGYQB >", value, "energyqb");
            return (Criteria) this;
        }

        public Criteria andEnergyqbGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("ENERGYQB >=", value, "energyqb");
            return (Criteria) this;
        }

        public Criteria andEnergyqbLessThan(BigDecimal value) {
            addCriterion("ENERGYQB <", value, "energyqb");
            return (Criteria) this;
        }

        public Criteria andEnergyqbLessThanOrEqualTo(BigDecimal value) {
            addCriterion("ENERGYQB <=", value, "energyqb");
            return (Criteria) this;
        }

        public Criteria andEnergyqbIn(List<BigDecimal> values) {
            addCriterion("ENERGYQB in", values, "energyqb");
            return (Criteria) this;
        }

        public Criteria andEnergyqbNotIn(List<BigDecimal> values) {
            addCriterion("ENERGYQB not in", values, "energyqb");
            return (Criteria) this;
        }

        public Criteria andEnergyqbBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ENERGYQB between", value1, value2, "energyqb");
            return (Criteria) this;
        }

        public Criteria andEnergyqbNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ENERGYQB not between", value1, value2, "energyqb");
            return (Criteria) this;
        }

        public Criteria andEnergyqcIsNull() {
            addCriterion("ENERGYQC is null");
            return (Criteria) this;
        }

        public Criteria andEnergyqcIsNotNull() {
            addCriterion("ENERGYQC is not null");
            return (Criteria) this;
        }

        public Criteria andEnergyqcEqualTo(BigDecimal value) {
            addCriterion("ENERGYQC =", value, "energyqc");
            return (Criteria) this;
        }

        public Criteria andEnergyqcNotEqualTo(BigDecimal value) {
            addCriterion("ENERGYQC <>", value, "energyqc");
            return (Criteria) this;
        }

        public Criteria andEnergyqcGreaterThan(BigDecimal value) {
            addCriterion("ENERGYQC >", value, "energyqc");
            return (Criteria) this;
        }

        public Criteria andEnergyqcGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("ENERGYQC >=", value, "energyqc");
            return (Criteria) this;
        }

        public Criteria andEnergyqcLessThan(BigDecimal value) {
            addCriterion("ENERGYQC <", value, "energyqc");
            return (Criteria) this;
        }

        public Criteria andEnergyqcLessThanOrEqualTo(BigDecimal value) {
            addCriterion("ENERGYQC <=", value, "energyqc");
            return (Criteria) this;
        }

        public Criteria andEnergyqcIn(List<BigDecimal> values) {
            addCriterion("ENERGYQC in", values, "energyqc");
            return (Criteria) this;
        }

        public Criteria andEnergyqcNotIn(List<BigDecimal> values) {
            addCriterion("ENERGYQC not in", values, "energyqc");
            return (Criteria) this;
        }

        public Criteria andEnergyqcBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ENERGYQC between", value1, value2, "energyqc");
            return (Criteria) this;
        }

        public Criteria andEnergyqcNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ENERGYQC not between", value1, value2, "energyqc");
            return (Criteria) this;
        }

        public Criteria andEnergyapIsNull() {
            addCriterion("ENERGYAP is null");
            return (Criteria) this;
        }

        public Criteria andEnergyapIsNotNull() {
            addCriterion("ENERGYAP is not null");
            return (Criteria) this;
        }

        public Criteria andEnergyapEqualTo(BigDecimal value) {
            addCriterion("ENERGYAP =", value, "energyap");
            return (Criteria) this;
        }

        public Criteria andEnergyapNotEqualTo(BigDecimal value) {
            addCriterion("ENERGYAP <>", value, "energyap");
            return (Criteria) this;
        }

        public Criteria andEnergyapGreaterThan(BigDecimal value) {
            addCriterion("ENERGYAP >", value, "energyap");
            return (Criteria) this;
        }

        public Criteria andEnergyapGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("ENERGYAP >=", value, "energyap");
            return (Criteria) this;
        }

        public Criteria andEnergyapLessThan(BigDecimal value) {
            addCriterion("ENERGYAP <", value, "energyap");
            return (Criteria) this;
        }

        public Criteria andEnergyapLessThanOrEqualTo(BigDecimal value) {
            addCriterion("ENERGYAP <=", value, "energyap");
            return (Criteria) this;
        }

        public Criteria andEnergyapIn(List<BigDecimal> values) {
            addCriterion("ENERGYAP in", values, "energyap");
            return (Criteria) this;
        }

        public Criteria andEnergyapNotIn(List<BigDecimal> values) {
            addCriterion("ENERGYAP not in", values, "energyap");
            return (Criteria) this;
        }

        public Criteria andEnergyapBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ENERGYAP between", value1, value2, "energyap");
            return (Criteria) this;
        }

        public Criteria andEnergyapNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ENERGYAP not between", value1, value2, "energyap");
            return (Criteria) this;
        }

        public Criteria andEnergyapaIsNull() {
            addCriterion("ENERGYAPA is null");
            return (Criteria) this;
        }

        public Criteria andEnergyapaIsNotNull() {
            addCriterion("ENERGYAPA is not null");
            return (Criteria) this;
        }

        public Criteria andEnergyapaEqualTo(BigDecimal value) {
            addCriterion("ENERGYAPA =", value, "energyapa");
            return (Criteria) this;
        }

        public Criteria andEnergyapaNotEqualTo(BigDecimal value) {
            addCriterion("ENERGYAPA <>", value, "energyapa");
            return (Criteria) this;
        }

        public Criteria andEnergyapaGreaterThan(BigDecimal value) {
            addCriterion("ENERGYAPA >", value, "energyapa");
            return (Criteria) this;
        }

        public Criteria andEnergyapaGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("ENERGYAPA >=", value, "energyapa");
            return (Criteria) this;
        }

        public Criteria andEnergyapaLessThan(BigDecimal value) {
            addCriterion("ENERGYAPA <", value, "energyapa");
            return (Criteria) this;
        }

        public Criteria andEnergyapaLessThanOrEqualTo(BigDecimal value) {
            addCriterion("ENERGYAPA <=", value, "energyapa");
            return (Criteria) this;
        }

        public Criteria andEnergyapaIn(List<BigDecimal> values) {
            addCriterion("ENERGYAPA in", values, "energyapa");
            return (Criteria) this;
        }

        public Criteria andEnergyapaNotIn(List<BigDecimal> values) {
            addCriterion("ENERGYAPA not in", values, "energyapa");
            return (Criteria) this;
        }

        public Criteria andEnergyapaBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ENERGYAPA between", value1, value2, "energyapa");
            return (Criteria) this;
        }

        public Criteria andEnergyapaNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ENERGYAPA not between", value1, value2, "energyapa");
            return (Criteria) this;
        }

        public Criteria andEnergyapbIsNull() {
            addCriterion("ENERGYAPB is null");
            return (Criteria) this;
        }

        public Criteria andEnergyapbIsNotNull() {
            addCriterion("ENERGYAPB is not null");
            return (Criteria) this;
        }

        public Criteria andEnergyapbEqualTo(BigDecimal value) {
            addCriterion("ENERGYAPB =", value, "energyapb");
            return (Criteria) this;
        }

        public Criteria andEnergyapbNotEqualTo(BigDecimal value) {
            addCriterion("ENERGYAPB <>", value, "energyapb");
            return (Criteria) this;
        }

        public Criteria andEnergyapbGreaterThan(BigDecimal value) {
            addCriterion("ENERGYAPB >", value, "energyapb");
            return (Criteria) this;
        }

        public Criteria andEnergyapbGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("ENERGYAPB >=", value, "energyapb");
            return (Criteria) this;
        }

        public Criteria andEnergyapbLessThan(BigDecimal value) {
            addCriterion("ENERGYAPB <", value, "energyapb");
            return (Criteria) this;
        }

        public Criteria andEnergyapbLessThanOrEqualTo(BigDecimal value) {
            addCriterion("ENERGYAPB <=", value, "energyapb");
            return (Criteria) this;
        }

        public Criteria andEnergyapbIn(List<BigDecimal> values) {
            addCriterion("ENERGYAPB in", values, "energyapb");
            return (Criteria) this;
        }

        public Criteria andEnergyapbNotIn(List<BigDecimal> values) {
            addCriterion("ENERGYAPB not in", values, "energyapb");
            return (Criteria) this;
        }

        public Criteria andEnergyapbBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ENERGYAPB between", value1, value2, "energyapb");
            return (Criteria) this;
        }

        public Criteria andEnergyapbNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ENERGYAPB not between", value1, value2, "energyapb");
            return (Criteria) this;
        }

        public Criteria andEnergyapcIsNull() {
            addCriterion("ENERGYAPC is null");
            return (Criteria) this;
        }

        public Criteria andEnergyapcIsNotNull() {
            addCriterion("ENERGYAPC is not null");
            return (Criteria) this;
        }

        public Criteria andEnergyapcEqualTo(BigDecimal value) {
            addCriterion("ENERGYAPC =", value, "energyapc");
            return (Criteria) this;
        }

        public Criteria andEnergyapcNotEqualTo(BigDecimal value) {
            addCriterion("ENERGYAPC <>", value, "energyapc");
            return (Criteria) this;
        }

        public Criteria andEnergyapcGreaterThan(BigDecimal value) {
            addCriterion("ENERGYAPC >", value, "energyapc");
            return (Criteria) this;
        }

        public Criteria andEnergyapcGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("ENERGYAPC >=", value, "energyapc");
            return (Criteria) this;
        }

        public Criteria andEnergyapcLessThan(BigDecimal value) {
            addCriterion("ENERGYAPC <", value, "energyapc");
            return (Criteria) this;
        }

        public Criteria andEnergyapcLessThanOrEqualTo(BigDecimal value) {
            addCriterion("ENERGYAPC <=", value, "energyapc");
            return (Criteria) this;
        }

        public Criteria andEnergyapcIn(List<BigDecimal> values) {
            addCriterion("ENERGYAPC in", values, "energyapc");
            return (Criteria) this;
        }

        public Criteria andEnergyapcNotIn(List<BigDecimal> values) {
            addCriterion("ENERGYAPC not in", values, "energyapc");
            return (Criteria) this;
        }

        public Criteria andEnergyapcBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ENERGYAPC between", value1, value2, "energyapc");
            return (Criteria) this;
        }

        public Criteria andEnergyapcNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ENERGYAPC not between", value1, value2, "energyapc");
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