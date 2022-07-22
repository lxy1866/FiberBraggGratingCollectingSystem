package top.kaluna.modbusTcp.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class FbgValueExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public FbgValueExample() {
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
            criteria = new ArrayList<>();
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

        public Criteria andPhysicalValueInfoIdIsNull() {
            addCriterion("physical_value_info_id is null");
            return (Criteria) this;
        }

        public Criteria andPhysicalValueInfoIdIsNotNull() {
            addCriterion("physical_value_info_id is not null");
            return (Criteria) this;
        }

        public Criteria andPhysicalValueInfoIdEqualTo(Long value) {
            addCriterion("physical_value_info_id =", value, "physicalValueInfoId");
            return (Criteria) this;
        }

        public Criteria andPhysicalValueInfoIdNotEqualTo(Long value) {
            addCriterion("physical_value_info_id <>", value, "physicalValueInfoId");
            return (Criteria) this;
        }

        public Criteria andPhysicalValueInfoIdGreaterThan(Long value) {
            addCriterion("physical_value_info_id >", value, "physicalValueInfoId");
            return (Criteria) this;
        }

        public Criteria andPhysicalValueInfoIdGreaterThanOrEqualTo(Long value) {
            addCriterion("physical_value_info_id >=", value, "physicalValueInfoId");
            return (Criteria) this;
        }

        public Criteria andPhysicalValueInfoIdLessThan(Long value) {
            addCriterion("physical_value_info_id <", value, "physicalValueInfoId");
            return (Criteria) this;
        }

        public Criteria andPhysicalValueInfoIdLessThanOrEqualTo(Long value) {
            addCriterion("physical_value_info_id <=", value, "physicalValueInfoId");
            return (Criteria) this;
        }

        public Criteria andPhysicalValueInfoIdIn(List<Long> values) {
            addCriterion("physical_value_info_id in", values, "physicalValueInfoId");
            return (Criteria) this;
        }

        public Criteria andPhysicalValueInfoIdNotIn(List<Long> values) {
            addCriterion("physical_value_info_id not in", values, "physicalValueInfoId");
            return (Criteria) this;
        }

        public Criteria andPhysicalValueInfoIdBetween(Long value1, Long value2) {
            addCriterion("physical_value_info_id between", value1, value2, "physicalValueInfoId");
            return (Criteria) this;
        }

        public Criteria andPhysicalValueInfoIdNotBetween(Long value1, Long value2) {
            addCriterion("physical_value_info_id not between", value1, value2, "physicalValueInfoId");
            return (Criteria) this;
        }

        public Criteria andValueIsNull() {
            addCriterion("`value` is null");
            return (Criteria) this;
        }

        public Criteria andValueIsNotNull() {
            addCriterion("`value` is not null");
            return (Criteria) this;
        }

        public Criteria andValueEqualTo(BigDecimal value) {
            addCriterion("`value` =", value, "value");
            return (Criteria) this;
        }

        public Criteria andValueNotEqualTo(BigDecimal value) {
            addCriterion("`value` <>", value, "value");
            return (Criteria) this;
        }

        public Criteria andValueGreaterThan(BigDecimal value) {
            addCriterion("`value` >", value, "value");
            return (Criteria) this;
        }

        public Criteria andValueGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("`value` >=", value, "value");
            return (Criteria) this;
        }

        public Criteria andValueLessThan(BigDecimal value) {
            addCriterion("`value` <", value, "value");
            return (Criteria) this;
        }

        public Criteria andValueLessThanOrEqualTo(BigDecimal value) {
            addCriterion("`value` <=", value, "value");
            return (Criteria) this;
        }

        public Criteria andValueIn(List<BigDecimal> values) {
            addCriterion("`value` in", values, "value");
            return (Criteria) this;
        }

        public Criteria andValueNotIn(List<BigDecimal> values) {
            addCriterion("`value` not in", values, "value");
            return (Criteria) this;
        }

        public Criteria andValueBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("`value` between", value1, value2, "value");
            return (Criteria) this;
        }

        public Criteria andValueNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("`value` not between", value1, value2, "value");
            return (Criteria) this;
        }

        public Criteria andArrayNumIsNull() {
            addCriterion("array_num is null");
            return (Criteria) this;
        }

        public Criteria andArrayNumIsNotNull() {
            addCriterion("array_num is not null");
            return (Criteria) this;
        }

        public Criteria andArrayNumEqualTo(Integer value) {
            addCriterion("array_num =", value, "arrayNum");
            return (Criteria) this;
        }

        public Criteria andArrayNumNotEqualTo(Integer value) {
            addCriterion("array_num <>", value, "arrayNum");
            return (Criteria) this;
        }

        public Criteria andArrayNumGreaterThan(Integer value) {
            addCriterion("array_num >", value, "arrayNum");
            return (Criteria) this;
        }

        public Criteria andArrayNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("array_num >=", value, "arrayNum");
            return (Criteria) this;
        }

        public Criteria andArrayNumLessThan(Integer value) {
            addCriterion("array_num <", value, "arrayNum");
            return (Criteria) this;
        }

        public Criteria andArrayNumLessThanOrEqualTo(Integer value) {
            addCriterion("array_num <=", value, "arrayNum");
            return (Criteria) this;
        }

        public Criteria andArrayNumIn(List<Integer> values) {
            addCriterion("array_num in", values, "arrayNum");
            return (Criteria) this;
        }

        public Criteria andArrayNumNotIn(List<Integer> values) {
            addCriterion("array_num not in", values, "arrayNum");
            return (Criteria) this;
        }

        public Criteria andArrayNumBetween(Integer value1, Integer value2) {
            addCriterion("array_num between", value1, value2, "arrayNum");
            return (Criteria) this;
        }

        public Criteria andArrayNumNotBetween(Integer value1, Integer value2) {
            addCriterion("array_num not between", value1, value2, "arrayNum");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Long value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Long value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Long value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Long value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Long value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Long value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Long> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Long> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Long value1, Long value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Long value1, Long value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andChannelIsNull() {
            addCriterion("channel is null");
            return (Criteria) this;
        }

        public Criteria andChannelIsNotNull() {
            addCriterion("channel is not null");
            return (Criteria) this;
        }

        public Criteria andChannelEqualTo(Integer value) {
            addCriterion("channel =", value, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelNotEqualTo(Integer value) {
            addCriterion("channel <>", value, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelGreaterThan(Integer value) {
            addCriterion("channel >", value, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelGreaterThanOrEqualTo(Integer value) {
            addCriterion("channel >=", value, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelLessThan(Integer value) {
            addCriterion("channel <", value, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelLessThanOrEqualTo(Integer value) {
            addCriterion("channel <=", value, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelIn(List<Integer> values) {
            addCriterion("channel in", values, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelNotIn(List<Integer> values) {
            addCriterion("channel not in", values, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelBetween(Integer value1, Integer value2) {
            addCriterion("channel between", value1, value2, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelNotBetween(Integer value1, Integer value2) {
            addCriterion("channel not between", value1, value2, "channel");
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