package top.kaluna.modbusTcp.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class PhysicalValueExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PhysicalValueExample() {
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

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andVal1IsNull() {
            addCriterion("val1 is null");
            return (Criteria) this;
        }

        public Criteria andVal1IsNotNull() {
            addCriterion("val1 is not null");
            return (Criteria) this;
        }

        public Criteria andVal1EqualTo(BigDecimal value) {
            addCriterion("val1 =", value, "val1");
            return (Criteria) this;
        }

        public Criteria andVal1NotEqualTo(BigDecimal value) {
            addCriterion("val1 <>", value, "val1");
            return (Criteria) this;
        }

        public Criteria andVal1GreaterThan(BigDecimal value) {
            addCriterion("val1 >", value, "val1");
            return (Criteria) this;
        }

        public Criteria andVal1GreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("val1 >=", value, "val1");
            return (Criteria) this;
        }

        public Criteria andVal1LessThan(BigDecimal value) {
            addCriterion("val1 <", value, "val1");
            return (Criteria) this;
        }

        public Criteria andVal1LessThanOrEqualTo(BigDecimal value) {
            addCriterion("val1 <=", value, "val1");
            return (Criteria) this;
        }

        public Criteria andVal1In(List<BigDecimal> values) {
            addCriterion("val1 in", values, "val1");
            return (Criteria) this;
        }

        public Criteria andVal1NotIn(List<BigDecimal> values) {
            addCriterion("val1 not in", values, "val1");
            return (Criteria) this;
        }

        public Criteria andVal1Between(BigDecimal value1, BigDecimal value2) {
            addCriterion("val1 between", value1, value2, "val1");
            return (Criteria) this;
        }

        public Criteria andVal1NotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("val1 not between", value1, value2, "val1");
            return (Criteria) this;
        }

        public Criteria andVal2IsNull() {
            addCriterion("val2 is null");
            return (Criteria) this;
        }

        public Criteria andVal2IsNotNull() {
            addCriterion("val2 is not null");
            return (Criteria) this;
        }

        public Criteria andVal2EqualTo(BigDecimal value) {
            addCriterion("val2 =", value, "val2");
            return (Criteria) this;
        }

        public Criteria andVal2NotEqualTo(BigDecimal value) {
            addCriterion("val2 <>", value, "val2");
            return (Criteria) this;
        }

        public Criteria andVal2GreaterThan(BigDecimal value) {
            addCriterion("val2 >", value, "val2");
            return (Criteria) this;
        }

        public Criteria andVal2GreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("val2 >=", value, "val2");
            return (Criteria) this;
        }

        public Criteria andVal2LessThan(BigDecimal value) {
            addCriterion("val2 <", value, "val2");
            return (Criteria) this;
        }

        public Criteria andVal2LessThanOrEqualTo(BigDecimal value) {
            addCriterion("val2 <=", value, "val2");
            return (Criteria) this;
        }

        public Criteria andVal2In(List<BigDecimal> values) {
            addCriterion("val2 in", values, "val2");
            return (Criteria) this;
        }

        public Criteria andVal2NotIn(List<BigDecimal> values) {
            addCriterion("val2 not in", values, "val2");
            return (Criteria) this;
        }

        public Criteria andVal2Between(BigDecimal value1, BigDecimal value2) {
            addCriterion("val2 between", value1, value2, "val2");
            return (Criteria) this;
        }

        public Criteria andVal2NotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("val2 not between", value1, value2, "val2");
            return (Criteria) this;
        }

        public Criteria andVal3IsNull() {
            addCriterion("val3 is null");
            return (Criteria) this;
        }

        public Criteria andVal3IsNotNull() {
            addCriterion("val3 is not null");
            return (Criteria) this;
        }

        public Criteria andVal3EqualTo(BigDecimal value) {
            addCriterion("val3 =", value, "val3");
            return (Criteria) this;
        }

        public Criteria andVal3NotEqualTo(BigDecimal value) {
            addCriterion("val3 <>", value, "val3");
            return (Criteria) this;
        }

        public Criteria andVal3GreaterThan(BigDecimal value) {
            addCriterion("val3 >", value, "val3");
            return (Criteria) this;
        }

        public Criteria andVal3GreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("val3 >=", value, "val3");
            return (Criteria) this;
        }

        public Criteria andVal3LessThan(BigDecimal value) {
            addCriterion("val3 <", value, "val3");
            return (Criteria) this;
        }

        public Criteria andVal3LessThanOrEqualTo(BigDecimal value) {
            addCriterion("val3 <=", value, "val3");
            return (Criteria) this;
        }

        public Criteria andVal3In(List<BigDecimal> values) {
            addCriterion("val3 in", values, "val3");
            return (Criteria) this;
        }

        public Criteria andVal3NotIn(List<BigDecimal> values) {
            addCriterion("val3 not in", values, "val3");
            return (Criteria) this;
        }

        public Criteria andVal3Between(BigDecimal value1, BigDecimal value2) {
            addCriterion("val3 between", value1, value2, "val3");
            return (Criteria) this;
        }

        public Criteria andVal3NotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("val3 not between", value1, value2, "val3");
            return (Criteria) this;
        }

        public Criteria andVal4IsNull() {
            addCriterion("val4 is null");
            return (Criteria) this;
        }

        public Criteria andVal4IsNotNull() {
            addCriterion("val4 is not null");
            return (Criteria) this;
        }

        public Criteria andVal4EqualTo(BigDecimal value) {
            addCriterion("val4 =", value, "val4");
            return (Criteria) this;
        }

        public Criteria andVal4NotEqualTo(BigDecimal value) {
            addCriterion("val4 <>", value, "val4");
            return (Criteria) this;
        }

        public Criteria andVal4GreaterThan(BigDecimal value) {
            addCriterion("val4 >", value, "val4");
            return (Criteria) this;
        }

        public Criteria andVal4GreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("val4 >=", value, "val4");
            return (Criteria) this;
        }

        public Criteria andVal4LessThan(BigDecimal value) {
            addCriterion("val4 <", value, "val4");
            return (Criteria) this;
        }

        public Criteria andVal4LessThanOrEqualTo(BigDecimal value) {
            addCriterion("val4 <=", value, "val4");
            return (Criteria) this;
        }

        public Criteria andVal4In(List<BigDecimal> values) {
            addCriterion("val4 in", values, "val4");
            return (Criteria) this;
        }

        public Criteria andVal4NotIn(List<BigDecimal> values) {
            addCriterion("val4 not in", values, "val4");
            return (Criteria) this;
        }

        public Criteria andVal4Between(BigDecimal value1, BigDecimal value2) {
            addCriterion("val4 between", value1, value2, "val4");
            return (Criteria) this;
        }

        public Criteria andVal4NotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("val4 not between", value1, value2, "val4");
            return (Criteria) this;
        }

        public Criteria andVal5IsNull() {
            addCriterion("val5 is null");
            return (Criteria) this;
        }

        public Criteria andVal5IsNotNull() {
            addCriterion("val5 is not null");
            return (Criteria) this;
        }

        public Criteria andVal5EqualTo(BigDecimal value) {
            addCriterion("val5 =", value, "val5");
            return (Criteria) this;
        }

        public Criteria andVal5NotEqualTo(BigDecimal value) {
            addCriterion("val5 <>", value, "val5");
            return (Criteria) this;
        }

        public Criteria andVal5GreaterThan(BigDecimal value) {
            addCriterion("val5 >", value, "val5");
            return (Criteria) this;
        }

        public Criteria andVal5GreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("val5 >=", value, "val5");
            return (Criteria) this;
        }

        public Criteria andVal5LessThan(BigDecimal value) {
            addCriterion("val5 <", value, "val5");
            return (Criteria) this;
        }

        public Criteria andVal5LessThanOrEqualTo(BigDecimal value) {
            addCriterion("val5 <=", value, "val5");
            return (Criteria) this;
        }

        public Criteria andVal5In(List<BigDecimal> values) {
            addCriterion("val5 in", values, "val5");
            return (Criteria) this;
        }

        public Criteria andVal5NotIn(List<BigDecimal> values) {
            addCriterion("val5 not in", values, "val5");
            return (Criteria) this;
        }

        public Criteria andVal5Between(BigDecimal value1, BigDecimal value2) {
            addCriterion("val5 between", value1, value2, "val5");
            return (Criteria) this;
        }

        public Criteria andVal5NotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("val5 not between", value1, value2, "val5");
            return (Criteria) this;
        }

        public Criteria andVal6IsNull() {
            addCriterion("val6 is null");
            return (Criteria) this;
        }

        public Criteria andVal6IsNotNull() {
            addCriterion("val6 is not null");
            return (Criteria) this;
        }

        public Criteria andVal6EqualTo(BigDecimal value) {
            addCriterion("val6 =", value, "val6");
            return (Criteria) this;
        }

        public Criteria andVal6NotEqualTo(BigDecimal value) {
            addCriterion("val6 <>", value, "val6");
            return (Criteria) this;
        }

        public Criteria andVal6GreaterThan(BigDecimal value) {
            addCriterion("val6 >", value, "val6");
            return (Criteria) this;
        }

        public Criteria andVal6GreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("val6 >=", value, "val6");
            return (Criteria) this;
        }

        public Criteria andVal6LessThan(BigDecimal value) {
            addCriterion("val6 <", value, "val6");
            return (Criteria) this;
        }

        public Criteria andVal6LessThanOrEqualTo(BigDecimal value) {
            addCriterion("val6 <=", value, "val6");
            return (Criteria) this;
        }

        public Criteria andVal6In(List<BigDecimal> values) {
            addCriterion("val6 in", values, "val6");
            return (Criteria) this;
        }

        public Criteria andVal6NotIn(List<BigDecimal> values) {
            addCriterion("val6 not in", values, "val6");
            return (Criteria) this;
        }

        public Criteria andVal6Between(BigDecimal value1, BigDecimal value2) {
            addCriterion("val6 between", value1, value2, "val6");
            return (Criteria) this;
        }

        public Criteria andVal6NotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("val6 not between", value1, value2, "val6");
            return (Criteria) this;
        }

        public Criteria andTagIsNull() {
            addCriterion("tag is null");
            return (Criteria) this;
        }

        public Criteria andTagIsNotNull() {
            addCriterion("tag is not null");
            return (Criteria) this;
        }

        public Criteria andTagEqualTo(Byte value) {
            addCriterion("tag =", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagNotEqualTo(Byte value) {
            addCriterion("tag <>", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagGreaterThan(Byte value) {
            addCriterion("tag >", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagGreaterThanOrEqualTo(Byte value) {
            addCriterion("tag >=", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagLessThan(Byte value) {
            addCriterion("tag <", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagLessThanOrEqualTo(Byte value) {
            addCriterion("tag <=", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagIn(List<Byte> values) {
            addCriterion("tag in", values, "tag");
            return (Criteria) this;
        }

        public Criteria andTagNotIn(List<Byte> values) {
            addCriterion("tag not in", values, "tag");
            return (Criteria) this;
        }

        public Criteria andTagBetween(Byte value1, Byte value2) {
            addCriterion("tag between", value1, value2, "tag");
            return (Criteria) this;
        }

        public Criteria andTagNotBetween(Byte value1, Byte value2) {
            addCriterion("tag not between", value1, value2, "tag");
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