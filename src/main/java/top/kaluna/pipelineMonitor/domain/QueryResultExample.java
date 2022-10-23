package top.kaluna.pipelineMonitor.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class QueryResultExample {

    protected String orderByClause;

    protected boolean distinct;

    protected List<QueryResultExample.Criteria> oredCriteria;

    public QueryResultExample() {
        oredCriteria = new ArrayList<>();
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

    public List<QueryResultExample.Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(QueryResultExample.Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public QueryResultExample.Criteria or() {
        QueryResultExample.Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public QueryResultExample.Criteria createCriteria() {
        QueryResultExample.Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected QueryResultExample.Criteria createCriteriaInternal() {
        QueryResultExample.Criteria criteria = new QueryResultExample.Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<QueryResultExample.Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<QueryResultExample.Criterion> getAllCriteria() {
            return criteria;
        }

        public List<QueryResultExample.Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new QueryResultExample.Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new QueryResultExample.Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new QueryResultExample.Criterion(condition, value1, value2));
        }

        public QueryResultExample.Criteria andIdIsNull() {
            addCriterion("id is null");
            return (QueryResultExample.Criteria) this;
        }

        public QueryResultExample.Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (QueryResultExample.Criteria) this;
        }

        public QueryResultExample.Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (QueryResultExample.Criteria) this;
        }

        public QueryResultExample.Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (QueryResultExample.Criteria) this;
        }

        public QueryResultExample.Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (QueryResultExample.Criteria) this;
        }

        public QueryResultExample.Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (QueryResultExample.Criteria) this;
        }

        public QueryResultExample.Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (QueryResultExample.Criteria) this;
        }

        public QueryResultExample.Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (QueryResultExample.Criteria) this;
        }

        public QueryResultExample.Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (QueryResultExample.Criteria) this;
        }

        public QueryResultExample.Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (QueryResultExample.Criteria) this;
        }

        public QueryResultExample.Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (QueryResultExample.Criteria) this;
        }

        public QueryResultExample.Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (QueryResultExample.Criteria) this;
        }

        public QueryResultExample.Criteria andPhysicalValueInfoIdIsNull() {
            addCriterion("physical_value_info_id is null");
            return (QueryResultExample.Criteria) this;
        }

        public QueryResultExample.Criteria andPhysicalValueInfoIdIsNotNull() {
            addCriterion("physical_value_info_id is not null");
            return (QueryResultExample.Criteria) this;
        }

        public QueryResultExample.Criteria andPhysicalValueInfoIdEqualTo(Long value) {
            addCriterion("physical_value_info_id =", value, "physicalValueInfoId");
            return (QueryResultExample.Criteria) this;
        }

        public QueryResultExample.Criteria andPhysicalValueInfoIdNotEqualTo(Long value) {
            addCriterion("physical_value_info_id <>", value, "physicalValueInfoId");
            return (QueryResultExample.Criteria) this;
        }

        public QueryResultExample.Criteria andPhysicalValueInfoIdGreaterThan(Long value) {
            addCriterion("physical_value_info_id >", value, "physicalValueInfoId");
            return (QueryResultExample.Criteria) this;
        }

        public QueryResultExample.Criteria andPhysicalValueInfoIdGreaterThanOrEqualTo(Long value) {
            addCriterion("physical_value_info_id >=", value, "physicalValueInfoId");
            return (QueryResultExample.Criteria) this;
        }

        public QueryResultExample.Criteria andPhysicalValueInfoIdLessThan(Long value) {
            addCriterion("physical_value_info_id <", value, "physicalValueInfoId");
            return (QueryResultExample.Criteria) this;
        }

        public QueryResultExample.Criteria andPhysicalValueInfoIdLessThanOrEqualTo(Long value) {
            addCriterion("physical_value_info_id <=", value, "physicalValueInfoId");
            return (QueryResultExample.Criteria) this;
        }

        public QueryResultExample.Criteria andPhysicalValueInfoIdIn(List<Long> values) {
            addCriterion("physical_value_info_id in", values, "physicalValueInfoId");
            return (QueryResultExample.Criteria) this;
        }

        public QueryResultExample.Criteria andPhysicalValueInfoIdNotIn(List<Long> values) {
            addCriterion("physical_value_info_id not in", values, "physicalValueInfoId");
            return (QueryResultExample.Criteria) this;
        }

        public QueryResultExample.Criteria andPhysicalValueInfoIdBetween(Long value1, Long value2) {
            addCriterion("physical_value_info_id between", value1, value2, "physicalValueInfoId");
            return (QueryResultExample.Criteria) this;
        }

        public QueryResultExample.Criteria andPhysicalValueInfoIdNotBetween(Long value1, Long value2) {
            addCriterion("physical_value_info_id not between", value1, value2, "physicalValueInfoId");
            return (QueryResultExample.Criteria) this;
        }

        public QueryResultExample.Criteria andValueIsNull() {
            addCriterion("`value` is null");
            return (QueryResultExample.Criteria) this;
        }

        public QueryResultExample.Criteria andValueIsNotNull() {
            addCriterion("`value` is not null");
            return (QueryResultExample.Criteria) this;
        }

        public QueryResultExample.Criteria andValueEqualTo(BigDecimal value) {
            addCriterion("`value` =", value, "value");
            return (QueryResultExample.Criteria) this;
        }

        public QueryResultExample.Criteria andValueNotEqualTo(BigDecimal value) {
            addCriterion("`value` <>", value, "value");
            return (QueryResultExample.Criteria) this;
        }

        public QueryResultExample.Criteria andValueGreaterThan(BigDecimal value) {
            addCriterion("`value` >", value, "value");
            return (QueryResultExample.Criteria) this;
        }

        public QueryResultExample.Criteria andValueGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("`value` >=", value, "value");
            return (QueryResultExample.Criteria) this;
        }

        public QueryResultExample.Criteria andValueLessThan(BigDecimal value) {
            addCriterion("`value` <", value, "value");
            return (QueryResultExample.Criteria) this;
        }

        public QueryResultExample.Criteria andValueLessThanOrEqualTo(BigDecimal value) {
            addCriterion("`value` <=", value, "value");
            return (QueryResultExample.Criteria) this;
        }

        public QueryResultExample.Criteria andValueIn(List<BigDecimal> values) {
            addCriterion("`value` in", values, "value");
            return (QueryResultExample.Criteria) this;
        }

        public QueryResultExample.Criteria andValueNotIn(List<BigDecimal> values) {
            addCriterion("`value` not in", values, "value");
            return (QueryResultExample.Criteria) this;
        }

        public QueryResultExample.Criteria andValueBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("`value` between", value1, value2, "value");
            return (QueryResultExample.Criteria) this;
        }

        public QueryResultExample.Criteria andValueNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("`value` not between", value1, value2, "value");
            return (QueryResultExample.Criteria) this;
        }

        public QueryResultExample.Criteria andArrayNumIsNull() {
            addCriterion("array_num is null");
            return (QueryResultExample.Criteria) this;
        }

        public QueryResultExample.Criteria andArrayNumIsNotNull() {
            addCriterion("array_num is not null");
            return (QueryResultExample.Criteria) this;
        }

        public QueryResultExample.Criteria andArrayNumEqualTo(Integer value) {
            addCriterion("array_num =", value, "arrayNum");
            return (QueryResultExample.Criteria) this;
        }

        public QueryResultExample.Criteria andArrayNumNotEqualTo(Integer value) {
            addCriterion("array_num <>", value, "arrayNum");
            return (QueryResultExample.Criteria) this;
        }

        public QueryResultExample.Criteria andArrayNumGreaterThan(Integer value) {
            addCriterion("array_num >", value, "arrayNum");
            return (QueryResultExample.Criteria) this;
        }

        public QueryResultExample.Criteria andArrayNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("array_num >=", value, "arrayNum");
            return (QueryResultExample.Criteria) this;
        }

        public QueryResultExample.Criteria andArrayNumLessThan(Integer value) {
            addCriterion("array_num <", value, "arrayNum");
            return (QueryResultExample.Criteria) this;
        }

        public QueryResultExample.Criteria andArrayNumLessThanOrEqualTo(Integer value) {
            addCriterion("array_num <=", value, "arrayNum");
            return (QueryResultExample.Criteria) this;
        }

        public QueryResultExample.Criteria andArrayNumIn(List<Integer> values) {
            addCriterion("array_num in", values, "arrayNum");
            return (QueryResultExample.Criteria) this;
        }

        public QueryResultExample.Criteria andArrayNumNotIn(List<Integer> values) {
            addCriterion("array_num not in", values, "arrayNum");
            return (QueryResultExample.Criteria) this;
        }

        public QueryResultExample.Criteria andArrayNumBetween(Integer value1, Integer value2) {
            addCriterion("array_num between", value1, value2, "arrayNum");
            return (QueryResultExample.Criteria) this;
        }

        public QueryResultExample.Criteria andArrayNumNotBetween(Integer value1, Integer value2) {
            addCriterion("array_num not between", value1, value2, "arrayNum");
            return (QueryResultExample.Criteria) this;
        }

        public QueryResultExample.Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (QueryResultExample.Criteria) this;
        }

        public QueryResultExample.Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (QueryResultExample.Criteria) this;
        }

        public QueryResultExample.Criteria andCreateTimeEqualTo(Long value) {
            addCriterion("create_time =", value, "createTime");
            return (QueryResultExample.Criteria) this;
        }

        public QueryResultExample.Criteria andCreateTimeNotEqualTo(Long value) {
            addCriterion("create_time <>", value, "createTime");
            return (QueryResultExample.Criteria) this;
        }

        public QueryResultExample.Criteria andCreateTimeGreaterThan(Long value) {
            addCriterion("create_time >", value, "createTime");
            return (QueryResultExample.Criteria) this;
        }

        public QueryResultExample.Criteria andCreateTimeGreaterThanOrEqualTo(Long value) {
            addCriterion("create_time >=", value, "createTime");
            return (QueryResultExample.Criteria) this;
        }

        public QueryResultExample.Criteria andCreateTimeLessThan(Long value) {
            addCriterion("create_time <", value, "createTime");
            return (QueryResultExample.Criteria) this;
        }

        public QueryResultExample.Criteria andCreateTimeLessThanOrEqualTo(Long value) {
            addCriterion("create_time <=", value, "createTime");
            return (QueryResultExample.Criteria) this;
        }

        public QueryResultExample.Criteria andCreateTimeIn(List<Long> values) {
            addCriterion("create_time in", values, "createTime");
            return (QueryResultExample.Criteria) this;
        }

        public QueryResultExample.Criteria andCreateTimeNotIn(List<Long> values) {
            addCriterion("create_time not in", values, "createTime");
            return (QueryResultExample.Criteria) this;
        }

        public QueryResultExample.Criteria andCreateTimeBetween(Long value1, Long value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (QueryResultExample.Criteria) this;
        }

        public QueryResultExample.Criteria andCreateTimeNotBetween(Long value1, Long value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (QueryResultExample.Criteria) this;
        }

        public QueryResultExample.Criteria andChannelIsNull() {
            addCriterion("channel is null");
            return (QueryResultExample.Criteria) this;
        }

        public QueryResultExample.Criteria andChannelIsNotNull() {
            addCriterion("channel is not null");
            return (QueryResultExample.Criteria) this;
        }

        public QueryResultExample.Criteria andChannelEqualTo(Integer value) {
            addCriterion("channel =", value, "channel");
            return (QueryResultExample.Criteria) this;
        }

        public QueryResultExample.Criteria andChannelNotEqualTo(Integer value) {
            addCriterion("channel <>", value, "channel");
            return (QueryResultExample.Criteria) this;
        }

        public QueryResultExample.Criteria andChannelGreaterThan(Integer value) {
            addCriterion("channel >", value, "channel");
            return (QueryResultExample.Criteria) this;
        }

        public QueryResultExample.Criteria andChannelGreaterThanOrEqualTo(Integer value) {
            addCriterion("channel >=", value, "channel");
            return (QueryResultExample.Criteria) this;
        }

        public QueryResultExample.Criteria andChannelLessThan(Integer value) {
            addCriterion("channel <", value, "channel");
            return (QueryResultExample.Criteria) this;
        }

        public QueryResultExample.Criteria andChannelLessThanOrEqualTo(Integer value) {
            addCriterion("channel <=", value, "channel");
            return (QueryResultExample.Criteria) this;
        }

        public QueryResultExample.Criteria andChannelIn(List<Integer> values) {
            addCriterion("channel in", values, "channel");
            return (QueryResultExample.Criteria) this;
        }

        public QueryResultExample.Criteria andChannelNotIn(List<Integer> values) {
            addCriterion("channel not in", values, "channel");
            return (QueryResultExample.Criteria) this;
        }

        public QueryResultExample.Criteria andChannelBetween(Integer value1, Integer value2) {
            addCriterion("channel between", value1, value2, "channel");
            return (QueryResultExample.Criteria) this;
        }

        public QueryResultExample.Criteria andChannelNotBetween(Integer value1, Integer value2) {
            addCriterion("channel not between", value1, value2, "channel");
            return (QueryResultExample.Criteria) this;
        }
    }

    public static class Criteria extends QueryResultExample.GeneratedCriteria {
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
