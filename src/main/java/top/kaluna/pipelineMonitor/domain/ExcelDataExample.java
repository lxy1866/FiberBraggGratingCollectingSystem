package top.kaluna.pipelineMonitor.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExcelDataExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ExcelDataExample() {
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

        public Criteria andArraySnIsNull() {
            addCriterion("array_sn is null");
            return (Criteria) this;
        }

        public Criteria andArraySnIsNotNull() {
            addCriterion("array_sn is not null");
            return (Criteria) this;
        }

        public Criteria andArraySnEqualTo(Integer value) {
            addCriterion("array_sn =", value, "arraySn");
            return (Criteria) this;
        }

        public Criteria andArraySnNotEqualTo(Integer value) {
            addCriterion("array_sn <>", value, "arraySn");
            return (Criteria) this;
        }

        public Criteria andArraySnGreaterThan(Integer value) {
            addCriterion("array_sn >", value, "arraySn");
            return (Criteria) this;
        }

        public Criteria andArraySnGreaterThanOrEqualTo(Integer value) {
            addCriterion("array_sn >=", value, "arraySn");
            return (Criteria) this;
        }

        public Criteria andArraySnLessThan(Integer value) {
            addCriterion("array_sn <", value, "arraySn");
            return (Criteria) this;
        }

        public Criteria andArraySnLessThanOrEqualTo(Integer value) {
            addCriterion("array_sn <=", value, "arraySn");
            return (Criteria) this;
        }

        public Criteria andArraySnIn(List<Integer> values) {
            addCriterion("array_sn in", values, "arraySn");
            return (Criteria) this;
        }

        public Criteria andArraySnNotIn(List<Integer> values) {
            addCriterion("array_sn not in", values, "arraySn");
            return (Criteria) this;
        }

        public Criteria andArraySnBetween(Integer value1, Integer value2) {
            addCriterion("array_sn between", value1, value2, "arraySn");
            return (Criteria) this;
        }

        public Criteria andArraySnNotBetween(Integer value1, Integer value2) {
            addCriterion("array_sn not between", value1, value2, "arraySn");
            return (Criteria) this;
        }

        public Criteria andSensorNodeNameIsNull() {
            addCriterion("sensor_node_name is null");
            return (Criteria) this;
        }

        public Criteria andSensorNodeNameIsNotNull() {
            addCriterion("sensor_node_name is not null");
            return (Criteria) this;
        }

        public Criteria andSensorNodeNameEqualTo(String value) {
            addCriterion("sensor_node_name =", value, "sensorNodeName");
            return (Criteria) this;
        }

        public Criteria andSensorNodeNameNotEqualTo(String value) {
            addCriterion("sensor_node_name <>", value, "sensorNodeName");
            return (Criteria) this;
        }

        public Criteria andSensorNodeNameGreaterThan(String value) {
            addCriterion("sensor_node_name >", value, "sensorNodeName");
            return (Criteria) this;
        }

        public Criteria andSensorNodeNameGreaterThanOrEqualTo(String value) {
            addCriterion("sensor_node_name >=", value, "sensorNodeName");
            return (Criteria) this;
        }

        public Criteria andSensorNodeNameLessThan(String value) {
            addCriterion("sensor_node_name <", value, "sensorNodeName");
            return (Criteria) this;
        }

        public Criteria andSensorNodeNameLessThanOrEqualTo(String value) {
            addCriterion("sensor_node_name <=", value, "sensorNodeName");
            return (Criteria) this;
        }

        public Criteria andSensorNodeNameLike(String value) {
            addCriterion("sensor_node_name like", value, "sensorNodeName");
            return (Criteria) this;
        }

        public Criteria andSensorNodeNameNotLike(String value) {
            addCriterion("sensor_node_name not like", value, "sensorNodeName");
            return (Criteria) this;
        }

        public Criteria andSensorNodeNameIn(List<String> values) {
            addCriterion("sensor_node_name in", values, "sensorNodeName");
            return (Criteria) this;
        }

        public Criteria andSensorNodeNameNotIn(List<String> values) {
            addCriterion("sensor_node_name not in", values, "sensorNodeName");
            return (Criteria) this;
        }

        public Criteria andSensorNodeNameBetween(String value1, String value2) {
            addCriterion("sensor_node_name between", value1, value2, "sensorNodeName");
            return (Criteria) this;
        }

        public Criteria andSensorNodeNameNotBetween(String value1, String value2) {
            addCriterion("sensor_node_name not between", value1, value2, "sensorNodeName");
            return (Criteria) this;
        }

        public Criteria andXValueIsNull() {
            addCriterion("x_value is null");
            return (Criteria) this;
        }

        public Criteria andXValueIsNotNull() {
            addCriterion("x_value is not null");
            return (Criteria) this;
        }

        public Criteria andXValueEqualTo(Double value) {
            addCriterion("x_value =", value, "xValue");
            return (Criteria) this;
        }

        public Criteria andXValueNotEqualTo(Double value) {
            addCriterion("x_value <>", value, "xValue");
            return (Criteria) this;
        }

        public Criteria andXValueGreaterThan(Double value) {
            addCriterion("x_value >", value, "xValue");
            return (Criteria) this;
        }

        public Criteria andXValueGreaterThanOrEqualTo(Double value) {
            addCriterion("x_value >=", value, "xValue");
            return (Criteria) this;
        }

        public Criteria andXValueLessThan(Double value) {
            addCriterion("x_value <", value, "xValue");
            return (Criteria) this;
        }

        public Criteria andXValueLessThanOrEqualTo(Double value) {
            addCriterion("x_value <=", value, "xValue");
            return (Criteria) this;
        }

        public Criteria andXValueIn(List<Double> values) {
            addCriterion("x_value in", values, "xValue");
            return (Criteria) this;
        }

        public Criteria andXValueNotIn(List<Double> values) {
            addCriterion("x_value not in", values, "xValue");
            return (Criteria) this;
        }

        public Criteria andXValueBetween(Double value1, Double value2) {
            addCriterion("x_value between", value1, value2, "xValue");
            return (Criteria) this;
        }

        public Criteria andXValueNotBetween(Double value1, Double value2) {
            addCriterion("x_value not between", value1, value2, "xValue");
            return (Criteria) this;
        }

        public Criteria andYValueIsNull() {
            addCriterion("y_value is null");
            return (Criteria) this;
        }

        public Criteria andYValueIsNotNull() {
            addCriterion("y_value is not null");
            return (Criteria) this;
        }

        public Criteria andYValueEqualTo(Double value) {
            addCriterion("y_value =", value, "yValue");
            return (Criteria) this;
        }

        public Criteria andYValueNotEqualTo(Double value) {
            addCriterion("y_value <>", value, "yValue");
            return (Criteria) this;
        }

        public Criteria andYValueGreaterThan(Double value) {
            addCriterion("y_value >", value, "yValue");
            return (Criteria) this;
        }

        public Criteria andYValueGreaterThanOrEqualTo(Double value) {
            addCriterion("y_value >=", value, "yValue");
            return (Criteria) this;
        }

        public Criteria andYValueLessThan(Double value) {
            addCriterion("y_value <", value, "yValue");
            return (Criteria) this;
        }

        public Criteria andYValueLessThanOrEqualTo(Double value) {
            addCriterion("y_value <=", value, "yValue");
            return (Criteria) this;
        }

        public Criteria andYValueIn(List<Double> values) {
            addCriterion("y_value in", values, "yValue");
            return (Criteria) this;
        }

        public Criteria andYValueNotIn(List<Double> values) {
            addCriterion("y_value not in", values, "yValue");
            return (Criteria) this;
        }

        public Criteria andYValueBetween(Double value1, Double value2) {
            addCriterion("y_value between", value1, value2, "yValue");
            return (Criteria) this;
        }

        public Criteria andYValueNotBetween(Double value1, Double value2) {
            addCriterion("y_value not between", value1, value2, "yValue");
            return (Criteria) this;
        }

        public Criteria andZValueIsNull() {
            addCriterion("z_value is null");
            return (Criteria) this;
        }

        public Criteria andZValueIsNotNull() {
            addCriterion("z_value is not null");
            return (Criteria) this;
        }

        public Criteria andZValueEqualTo(Double value) {
            addCriterion("z_value =", value, "zValue");
            return (Criteria) this;
        }

        public Criteria andZValueNotEqualTo(Double value) {
            addCriterion("z_value <>", value, "zValue");
            return (Criteria) this;
        }

        public Criteria andZValueGreaterThan(Double value) {
            addCriterion("z_value >", value, "zValue");
            return (Criteria) this;
        }

        public Criteria andZValueGreaterThanOrEqualTo(Double value) {
            addCriterion("z_value >=", value, "zValue");
            return (Criteria) this;
        }

        public Criteria andZValueLessThan(Double value) {
            addCriterion("z_value <", value, "zValue");
            return (Criteria) this;
        }

        public Criteria andZValueLessThanOrEqualTo(Double value) {
            addCriterion("z_value <=", value, "zValue");
            return (Criteria) this;
        }

        public Criteria andZValueIn(List<Double> values) {
            addCriterion("z_value in", values, "zValue");
            return (Criteria) this;
        }

        public Criteria andZValueNotIn(List<Double> values) {
            addCriterion("z_value not in", values, "zValue");
            return (Criteria) this;
        }

        public Criteria andZValueBetween(Double value1, Double value2) {
            addCriterion("z_value between", value1, value2, "zValue");
            return (Criteria) this;
        }

        public Criteria andZValueNotBetween(Double value1, Double value2) {
            addCriterion("z_value not between", value1, value2, "zValue");
            return (Criteria) this;
        }

        public Criteria andDateIsNull() {
            addCriterion("`date` is null");
            return (Criteria) this;
        }

        public Criteria andDateIsNotNull() {
            addCriterion("`date` is not null");
            return (Criteria) this;
        }

        public Criteria andDateEqualTo(Date value) {
            addCriterion("`date` =", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateNotEqualTo(Date value) {
            addCriterion("`date` <>", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateGreaterThan(Date value) {
            addCriterion("`date` >", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateGreaterThanOrEqualTo(Date value) {
            addCriterion("`date` >=", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateLessThan(Date value) {
            addCriterion("`date` <", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateLessThanOrEqualTo(Date value) {
            addCriterion("`date` <=", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateIn(List<Date> values) {
            addCriterion("`date` in", values, "date");
            return (Criteria) this;
        }

        public Criteria andDateNotIn(List<Date> values) {
            addCriterion("`date` not in", values, "date");
            return (Criteria) this;
        }

        public Criteria andDateBetween(Date value1, Date value2) {
            addCriterion("`date` between", value1, value2, "date");
            return (Criteria) this;
        }

        public Criteria andDateNotBetween(Date value1, Date value2) {
            addCriterion("`date` not between", value1, value2, "date");
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