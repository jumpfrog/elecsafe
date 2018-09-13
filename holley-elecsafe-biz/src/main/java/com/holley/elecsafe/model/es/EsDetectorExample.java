package com.holley.elecsafe.model.es;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EsDetectorExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public EsDetectorExample() {
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

        public Criteria andNameIsNull() {
            addCriterion("NAME is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("NAME is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("NAME =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("NAME <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("NAME >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("NAME >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("NAME <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("NAME <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("NAME like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("NAME not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("NAME in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("NAME not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("NAME between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("NAME not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("TYPE is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(Short value) {
            addCriterion("TYPE =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(Short value) {
            addCriterion("TYPE <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(Short value) {
            addCriterion("TYPE >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(Short value) {
            addCriterion("TYPE >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(Short value) {
            addCriterion("TYPE <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(Short value) {
            addCriterion("TYPE <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<Short> values) {
            addCriterion("TYPE in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<Short> values) {
            addCriterion("TYPE not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(Short value1, Short value2) {
            addCriterion("TYPE between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(Short value1, Short value2) {
            addCriterion("TYPE not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("STATUS is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Short value) {
            addCriterion("STATUS =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Short value) {
            addCriterion("STATUS <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Short value) {
            addCriterion("STATUS >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Short value) {
            addCriterion("STATUS >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Short value) {
            addCriterion("STATUS <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Short value) {
            addCriterion("STATUS <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Short> values) {
            addCriterion("STATUS in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Short> values) {
            addCriterion("STATUS not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Short value1, Short value2) {
            addCriterion("STATUS between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Short value1, Short value2) {
            addCriterion("STATUS not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andModelidIsNull() {
            addCriterion("MODELID is null");
            return (Criteria) this;
        }

        public Criteria andModelidIsNotNull() {
            addCriterion("MODELID is not null");
            return (Criteria) this;
        }

        public Criteria andModelidEqualTo(Short value) {
            addCriterion("MODELID =", value, "modelid");
            return (Criteria) this;
        }

        public Criteria andModelidNotEqualTo(Short value) {
            addCriterion("MODELID <>", value, "modelid");
            return (Criteria) this;
        }

        public Criteria andModelidGreaterThan(Short value) {
            addCriterion("MODELID >", value, "modelid");
            return (Criteria) this;
        }

        public Criteria andModelidGreaterThanOrEqualTo(Short value) {
            addCriterion("MODELID >=", value, "modelid");
            return (Criteria) this;
        }

        public Criteria andModelidLessThan(Short value) {
            addCriterion("MODELID <", value, "modelid");
            return (Criteria) this;
        }

        public Criteria andModelidLessThanOrEqualTo(Short value) {
            addCriterion("MODELID <=", value, "modelid");
            return (Criteria) this;
        }

        public Criteria andModelidIn(List<Short> values) {
            addCriterion("MODELID in", values, "modelid");
            return (Criteria) this;
        }

        public Criteria andModelidNotIn(List<Short> values) {
            addCriterion("MODELID not in", values, "modelid");
            return (Criteria) this;
        }

        public Criteria andModelidBetween(Short value1, Short value2) {
            addCriterion("MODELID between", value1, value2, "modelid");
            return (Criteria) this;
        }

        public Criteria andModelidNotBetween(Short value1, Short value2) {
            addCriterion("MODELID not between", value1, value2, "modelid");
            return (Criteria) this;
        }

        public Criteria andCommaddrIsNull() {
            addCriterion("COMMADDR is null");
            return (Criteria) this;
        }

        public Criteria andCommaddrIsNotNull() {
            addCriterion("COMMADDR is not null");
            return (Criteria) this;
        }

        public Criteria andCommaddrEqualTo(String value) {
            addCriterion("COMMADDR =", value, "commaddr");
            return (Criteria) this;
        }

        public Criteria andCommaddrNotEqualTo(String value) {
            addCriterion("COMMADDR <>", value, "commaddr");
            return (Criteria) this;
        }

        public Criteria andCommaddrGreaterThan(String value) {
            addCriterion("COMMADDR >", value, "commaddr");
            return (Criteria) this;
        }

        public Criteria andCommaddrGreaterThanOrEqualTo(String value) {
            addCriterion("COMMADDR >=", value, "commaddr");
            return (Criteria) this;
        }

        public Criteria andCommaddrLessThan(String value) {
            addCriterion("COMMADDR <", value, "commaddr");
            return (Criteria) this;
        }

        public Criteria andCommaddrLessThanOrEqualTo(String value) {
            addCriterion("COMMADDR <=", value, "commaddr");
            return (Criteria) this;
        }

        public Criteria andCommaddrLike(String value) {
            addCriterion("COMMADDR like", value, "commaddr");
            return (Criteria) this;
        }

        public Criteria andCommaddrNotLike(String value) {
            addCriterion("COMMADDR not like", value, "commaddr");
            return (Criteria) this;
        }

        public Criteria andCommaddrIn(List<String> values) {
            addCriterion("COMMADDR in", values, "commaddr");
            return (Criteria) this;
        }

        public Criteria andCommaddrNotIn(List<String> values) {
            addCriterion("COMMADDR not in", values, "commaddr");
            return (Criteria) this;
        }

        public Criteria andCommaddrBetween(String value1, String value2) {
            addCriterion("COMMADDR between", value1, value2, "commaddr");
            return (Criteria) this;
        }

        public Criteria andCommaddrNotBetween(String value1, String value2) {
            addCriterion("COMMADDR not between", value1, value2, "commaddr");
            return (Criteria) this;
        }

        public Criteria andProtocolidIsNull() {
            addCriterion("PROTOCOLID is null");
            return (Criteria) this;
        }

        public Criteria andProtocolidIsNotNull() {
            addCriterion("PROTOCOLID is not null");
            return (Criteria) this;
        }

        public Criteria andProtocolidEqualTo(Short value) {
            addCriterion("PROTOCOLID =", value, "protocolid");
            return (Criteria) this;
        }

        public Criteria andProtocolidNotEqualTo(Short value) {
            addCriterion("PROTOCOLID <>", value, "protocolid");
            return (Criteria) this;
        }

        public Criteria andProtocolidGreaterThan(Short value) {
            addCriterion("PROTOCOLID >", value, "protocolid");
            return (Criteria) this;
        }

        public Criteria andProtocolidGreaterThanOrEqualTo(Short value) {
            addCriterion("PROTOCOLID >=", value, "protocolid");
            return (Criteria) this;
        }

        public Criteria andProtocolidLessThan(Short value) {
            addCriterion("PROTOCOLID <", value, "protocolid");
            return (Criteria) this;
        }

        public Criteria andProtocolidLessThanOrEqualTo(Short value) {
            addCriterion("PROTOCOLID <=", value, "protocolid");
            return (Criteria) this;
        }

        public Criteria andProtocolidIn(List<Short> values) {
            addCriterion("PROTOCOLID in", values, "protocolid");
            return (Criteria) this;
        }

        public Criteria andProtocolidNotIn(List<Short> values) {
            addCriterion("PROTOCOLID not in", values, "protocolid");
            return (Criteria) this;
        }

        public Criteria andProtocolidBetween(Short value1, Short value2) {
            addCriterion("PROTOCOLID between", value1, value2, "protocolid");
            return (Criteria) this;
        }

        public Criteria andProtocolidNotBetween(Short value1, Short value2) {
            addCriterion("PROTOCOLID not between", value1, value2, "protocolid");
            return (Criteria) this;
        }

        public Criteria andOwneridIsNull() {
            addCriterion("OWNERID is null");
            return (Criteria) this;
        }

        public Criteria andOwneridIsNotNull() {
            addCriterion("OWNERID is not null");
            return (Criteria) this;
        }

        public Criteria andOwneridEqualTo(Integer value) {
            addCriterion("OWNERID =", value, "ownerid");
            return (Criteria) this;
        }

        public Criteria andOwneridNotEqualTo(Integer value) {
            addCriterion("OWNERID <>", value, "ownerid");
            return (Criteria) this;
        }

        public Criteria andOwneridGreaterThan(Integer value) {
            addCriterion("OWNERID >", value, "ownerid");
            return (Criteria) this;
        }

        public Criteria andOwneridGreaterThanOrEqualTo(Integer value) {
            addCriterion("OWNERID >=", value, "ownerid");
            return (Criteria) this;
        }

        public Criteria andOwneridLessThan(Integer value) {
            addCriterion("OWNERID <", value, "ownerid");
            return (Criteria) this;
        }

        public Criteria andOwneridLessThanOrEqualTo(Integer value) {
            addCriterion("OWNERID <=", value, "ownerid");
            return (Criteria) this;
        }

        public Criteria andOwneridIn(List<Integer> values) {
            addCriterion("OWNERID in", values, "ownerid");
            return (Criteria) this;
        }

        public Criteria andOwneridNotIn(List<Integer> values) {
            addCriterion("OWNERID not in", values, "ownerid");
            return (Criteria) this;
        }

        public Criteria andOwneridBetween(Integer value1, Integer value2) {
            addCriterion("OWNERID between", value1, value2, "ownerid");
            return (Criteria) this;
        }

        public Criteria andOwneridNotBetween(Integer value1, Integer value2) {
            addCriterion("OWNERID not between", value1, value2, "ownerid");
            return (Criteria) this;
        }

        public Criteria andOwnertypeIsNull() {
            addCriterion("OWNERTYPE is null");
            return (Criteria) this;
        }

        public Criteria andOwnertypeIsNotNull() {
            addCriterion("OWNERTYPE is not null");
            return (Criteria) this;
        }

        public Criteria andOwnertypeEqualTo(Short value) {
            addCriterion("OWNERTYPE =", value, "ownertype");
            return (Criteria) this;
        }

        public Criteria andOwnertypeNotEqualTo(Short value) {
            addCriterion("OWNERTYPE <>", value, "ownertype");
            return (Criteria) this;
        }

        public Criteria andOwnertypeGreaterThan(Short value) {
            addCriterion("OWNERTYPE >", value, "ownertype");
            return (Criteria) this;
        }

        public Criteria andOwnertypeGreaterThanOrEqualTo(Short value) {
            addCriterion("OWNERTYPE >=", value, "ownertype");
            return (Criteria) this;
        }

        public Criteria andOwnertypeLessThan(Short value) {
            addCriterion("OWNERTYPE <", value, "ownertype");
            return (Criteria) this;
        }

        public Criteria andOwnertypeLessThanOrEqualTo(Short value) {
            addCriterion("OWNERTYPE <=", value, "ownertype");
            return (Criteria) this;
        }

        public Criteria andOwnertypeIn(List<Short> values) {
            addCriterion("OWNERTYPE in", values, "ownertype");
            return (Criteria) this;
        }

        public Criteria andOwnertypeNotIn(List<Short> values) {
            addCriterion("OWNERTYPE not in", values, "ownertype");
            return (Criteria) this;
        }

        public Criteria andOwnertypeBetween(Short value1, Short value2) {
            addCriterion("OWNERTYPE between", value1, value2, "ownertype");
            return (Criteria) this;
        }

        public Criteria andOwnertypeNotBetween(Short value1, Short value2) {
            addCriterion("OWNERTYPE not between", value1, value2, "ownertype");
            return (Criteria) this;
        }

        public Criteria andInstalladdrIsNull() {
            addCriterion("INSTALLADDR is null");
            return (Criteria) this;
        }

        public Criteria andInstalladdrIsNotNull() {
            addCriterion("INSTALLADDR is not null");
            return (Criteria) this;
        }

        public Criteria andInstalladdrEqualTo(String value) {
            addCriterion("INSTALLADDR =", value, "installaddr");
            return (Criteria) this;
        }

        public Criteria andInstalladdrNotEqualTo(String value) {
            addCriterion("INSTALLADDR <>", value, "installaddr");
            return (Criteria) this;
        }

        public Criteria andInstalladdrGreaterThan(String value) {
            addCriterion("INSTALLADDR >", value, "installaddr");
            return (Criteria) this;
        }

        public Criteria andInstalladdrGreaterThanOrEqualTo(String value) {
            addCriterion("INSTALLADDR >=", value, "installaddr");
            return (Criteria) this;
        }

        public Criteria andInstalladdrLessThan(String value) {
            addCriterion("INSTALLADDR <", value, "installaddr");
            return (Criteria) this;
        }

        public Criteria andInstalladdrLessThanOrEqualTo(String value) {
            addCriterion("INSTALLADDR <=", value, "installaddr");
            return (Criteria) this;
        }

        public Criteria andInstalladdrLike(String value) {
            addCriterion("INSTALLADDR like", value, "installaddr");
            return (Criteria) this;
        }

        public Criteria andInstalladdrNotLike(String value) {
            addCriterion("INSTALLADDR not like", value, "installaddr");
            return (Criteria) this;
        }

        public Criteria andInstalladdrIn(List<String> values) {
            addCriterion("INSTALLADDR in", values, "installaddr");
            return (Criteria) this;
        }

        public Criteria andInstalladdrNotIn(List<String> values) {
            addCriterion("INSTALLADDR not in", values, "installaddr");
            return (Criteria) this;
        }

        public Criteria andInstalladdrBetween(String value1, String value2) {
            addCriterion("INSTALLADDR between", value1, value2, "installaddr");
            return (Criteria) this;
        }

        public Criteria andInstalladdrNotBetween(String value1, String value2) {
            addCriterion("INSTALLADDR not between", value1, value2, "installaddr");
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