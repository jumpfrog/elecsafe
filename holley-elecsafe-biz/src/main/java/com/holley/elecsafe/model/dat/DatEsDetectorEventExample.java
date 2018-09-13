package com.holley.elecsafe.model.dat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DatEsDetectorEventExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DatEsDetectorEventExample() {
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

        public Criteria andEvttypeIsNull() {
            addCriterion("EVTTYPE is null");
            return (Criteria) this;
        }

        public Criteria andEvttypeIsNotNull() {
            addCriterion("EVTTYPE is not null");
            return (Criteria) this;
        }

        public Criteria andEvttypeEqualTo(Short value) {
            addCriterion("EVTTYPE =", value, "evttype");
            return (Criteria) this;
        }

        public Criteria andEvttypeNotEqualTo(Short value) {
            addCriterion("EVTTYPE <>", value, "evttype");
            return (Criteria) this;
        }

        public Criteria andEvttypeGreaterThan(Short value) {
            addCriterion("EVTTYPE >", value, "evttype");
            return (Criteria) this;
        }

        public Criteria andEvttypeGreaterThanOrEqualTo(Short value) {
            addCriterion("EVTTYPE >=", value, "evttype");
            return (Criteria) this;
        }

        public Criteria andEvttypeLessThan(Short value) {
            addCriterion("EVTTYPE <", value, "evttype");
            return (Criteria) this;
        }

        public Criteria andEvttypeLessThanOrEqualTo(Short value) {
            addCriterion("EVTTYPE <=", value, "evttype");
            return (Criteria) this;
        }

        public Criteria andEvttypeIn(List<Short> values) {
            addCriterion("EVTTYPE in", values, "evttype");
            return (Criteria) this;
        }

        public Criteria andEvttypeNotIn(List<Short> values) {
            addCriterion("EVTTYPE not in", values, "evttype");
            return (Criteria) this;
        }

        public Criteria andEvttypeBetween(Short value1, Short value2) {
            addCriterion("EVTTYPE between", value1, value2, "evttype");
            return (Criteria) this;
        }

        public Criteria andEvttypeNotBetween(Short value1, Short value2) {
            addCriterion("EVTTYPE not between", value1, value2, "evttype");
            return (Criteria) this;
        }

        public Criteria andEvtdescIsNull() {
            addCriterion("EVTDESC is null");
            return (Criteria) this;
        }

        public Criteria andEvtdescIsNotNull() {
            addCriterion("EVTDESC is not null");
            return (Criteria) this;
        }

        public Criteria andEvtdescEqualTo(String value) {
            addCriterion("EVTDESC =", value, "evtdesc");
            return (Criteria) this;
        }

        public Criteria andEvtdescNotEqualTo(String value) {
            addCriterion("EVTDESC <>", value, "evtdesc");
            return (Criteria) this;
        }

        public Criteria andEvtdescGreaterThan(String value) {
            addCriterion("EVTDESC >", value, "evtdesc");
            return (Criteria) this;
        }

        public Criteria andEvtdescGreaterThanOrEqualTo(String value) {
            addCriterion("EVTDESC >=", value, "evtdesc");
            return (Criteria) this;
        }

        public Criteria andEvtdescLessThan(String value) {
            addCriterion("EVTDESC <", value, "evtdesc");
            return (Criteria) this;
        }

        public Criteria andEvtdescLessThanOrEqualTo(String value) {
            addCriterion("EVTDESC <=", value, "evtdesc");
            return (Criteria) this;
        }

        public Criteria andEvtdescLike(String value) {
            addCriterion("EVTDESC like", value, "evtdesc");
            return (Criteria) this;
        }

        public Criteria andEvtdescNotLike(String value) {
            addCriterion("EVTDESC not like", value, "evtdesc");
            return (Criteria) this;
        }

        public Criteria andEvtdescIn(List<String> values) {
            addCriterion("EVTDESC in", values, "evtdesc");
            return (Criteria) this;
        }

        public Criteria andEvtdescNotIn(List<String> values) {
            addCriterion("EVTDESC not in", values, "evtdesc");
            return (Criteria) this;
        }

        public Criteria andEvtdescBetween(String value1, String value2) {
            addCriterion("EVTDESC between", value1, value2, "evtdesc");
            return (Criteria) this;
        }

        public Criteria andEvtdescNotBetween(String value1, String value2) {
            addCriterion("EVTDESC not between", value1, value2, "evtdesc");
            return (Criteria) this;
        }

        public Criteria andDealstatusIsNull() {
            addCriterion("DEALSTATUS is null");
            return (Criteria) this;
        }

        public Criteria andDealstatusIsNotNull() {
            addCriterion("DEALSTATUS is not null");
            return (Criteria) this;
        }

        public Criteria andDealstatusEqualTo(Short value) {
            addCriterion("DEALSTATUS =", value, "dealstatus");
            return (Criteria) this;
        }

        public Criteria andDealstatusNotEqualTo(Short value) {
            addCriterion("DEALSTATUS <>", value, "dealstatus");
            return (Criteria) this;
        }

        public Criteria andDealstatusGreaterThan(Short value) {
            addCriterion("DEALSTATUS >", value, "dealstatus");
            return (Criteria) this;
        }

        public Criteria andDealstatusGreaterThanOrEqualTo(Short value) {
            addCriterion("DEALSTATUS >=", value, "dealstatus");
            return (Criteria) this;
        }

        public Criteria andDealstatusLessThan(Short value) {
            addCriterion("DEALSTATUS <", value, "dealstatus");
            return (Criteria) this;
        }

        public Criteria andDealstatusLessThanOrEqualTo(Short value) {
            addCriterion("DEALSTATUS <=", value, "dealstatus");
            return (Criteria) this;
        }

        public Criteria andDealstatusIn(List<Short> values) {
            addCriterion("DEALSTATUS in", values, "dealstatus");
            return (Criteria) this;
        }

        public Criteria andDealstatusNotIn(List<Short> values) {
            addCriterion("DEALSTATUS not in", values, "dealstatus");
            return (Criteria) this;
        }

        public Criteria andDealstatusBetween(Short value1, Short value2) {
            addCriterion("DEALSTATUS between", value1, value2, "dealstatus");
            return (Criteria) this;
        }

        public Criteria andDealstatusNotBetween(Short value1, Short value2) {
            addCriterion("DEALSTATUS not between", value1, value2, "dealstatus");
            return (Criteria) this;
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