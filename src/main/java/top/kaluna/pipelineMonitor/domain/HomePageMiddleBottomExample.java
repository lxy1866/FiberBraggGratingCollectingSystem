package top.kaluna.pipelineMonitor.domain;

import java.util.ArrayList;
import java.util.List;

public class HomePageMiddleBottomExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public HomePageMiddleBottomExample() {
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

        public Criteria andCurveTotalIsNull() {
            addCriterion("curve_total is null");
            return (Criteria) this;
        }

        public Criteria andCurveTotalIsNotNull() {
            addCriterion("curve_total is not null");
            return (Criteria) this;
        }

        public Criteria andCurveTotalEqualTo(Long value) {
            addCriterion("curve_total =", value, "curveTotal");
            return (Criteria) this;
        }

        public Criteria andCurveTotalNotEqualTo(Long value) {
            addCriterion("curve_total <>", value, "curveTotal");
            return (Criteria) this;
        }

        public Criteria andCurveTotalGreaterThan(Long value) {
            addCriterion("curve_total >", value, "curveTotal");
            return (Criteria) this;
        }

        public Criteria andCurveTotalGreaterThanOrEqualTo(Long value) {
            addCriterion("curve_total >=", value, "curveTotal");
            return (Criteria) this;
        }

        public Criteria andCurveTotalLessThan(Long value) {
            addCriterion("curve_total <", value, "curveTotal");
            return (Criteria) this;
        }

        public Criteria andCurveTotalLessThanOrEqualTo(Long value) {
            addCriterion("curve_total <=", value, "curveTotal");
            return (Criteria) this;
        }

        public Criteria andCurveTotalIn(List<Long> values) {
            addCriterion("curve_total in", values, "curveTotal");
            return (Criteria) this;
        }

        public Criteria andCurveTotalNotIn(List<Long> values) {
            addCriterion("curve_total not in", values, "curveTotal");
            return (Criteria) this;
        }

        public Criteria andCurveTotalBetween(Long value1, Long value2) {
            addCriterion("curve_total between", value1, value2, "curveTotal");
            return (Criteria) this;
        }

        public Criteria andCurveTotalNotBetween(Long value1, Long value2) {
            addCriterion("curve_total not between", value1, value2, "curveTotal");
            return (Criteria) this;
        }

        public Criteria andArrayNodeIsNull() {
            addCriterion("array_node is null");
            return (Criteria) this;
        }

        public Criteria andArrayNodeIsNotNull() {
            addCriterion("array_node is not null");
            return (Criteria) this;
        }

        public Criteria andArrayNodeEqualTo(String value) {
            addCriterion("array_node =", value, "arrayNode");
            return (Criteria) this;
        }

        public Criteria andArrayNodeNotEqualTo(String value) {
            addCriterion("array_node <>", value, "arrayNode");
            return (Criteria) this;
        }

        public Criteria andArrayNodeGreaterThan(String value) {
            addCriterion("array_node >", value, "arrayNode");
            return (Criteria) this;
        }

        public Criteria andArrayNodeGreaterThanOrEqualTo(String value) {
            addCriterion("array_node >=", value, "arrayNode");
            return (Criteria) this;
        }

        public Criteria andArrayNodeLessThan(String value) {
            addCriterion("array_node <", value, "arrayNode");
            return (Criteria) this;
        }

        public Criteria andArrayNodeLessThanOrEqualTo(String value) {
            addCriterion("array_node <=", value, "arrayNode");
            return (Criteria) this;
        }

        public Criteria andArrayNodeLike(String value) {
            addCriterion("array_node like", value, "arrayNode");
            return (Criteria) this;
        }

        public Criteria andArrayNodeNotLike(String value) {
            addCriterion("array_node not like", value, "arrayNode");
            return (Criteria) this;
        }

        public Criteria andArrayNodeIn(List<String> values) {
            addCriterion("array_node in", values, "arrayNode");
            return (Criteria) this;
        }

        public Criteria andArrayNodeNotIn(List<String> values) {
            addCriterion("array_node not in", values, "arrayNode");
            return (Criteria) this;
        }

        public Criteria andArrayNodeBetween(String value1, String value2) {
            addCriterion("array_node between", value1, value2, "arrayNode");
            return (Criteria) this;
        }

        public Criteria andArrayNodeNotBetween(String value1, String value2) {
            addCriterion("array_node not between", value1, value2, "arrayNode");
            return (Criteria) this;
        }

        public Criteria andFileFormatIsNull() {
            addCriterion("file_format is null");
            return (Criteria) this;
        }

        public Criteria andFileFormatIsNotNull() {
            addCriterion("file_format is not null");
            return (Criteria) this;
        }

        public Criteria andFileFormatEqualTo(String value) {
            addCriterion("file_format =", value, "fileFormat");
            return (Criteria) this;
        }

        public Criteria andFileFormatNotEqualTo(String value) {
            addCriterion("file_format <>", value, "fileFormat");
            return (Criteria) this;
        }

        public Criteria andFileFormatGreaterThan(String value) {
            addCriterion("file_format >", value, "fileFormat");
            return (Criteria) this;
        }

        public Criteria andFileFormatGreaterThanOrEqualTo(String value) {
            addCriterion("file_format >=", value, "fileFormat");
            return (Criteria) this;
        }

        public Criteria andFileFormatLessThan(String value) {
            addCriterion("file_format <", value, "fileFormat");
            return (Criteria) this;
        }

        public Criteria andFileFormatLessThanOrEqualTo(String value) {
            addCriterion("file_format <=", value, "fileFormat");
            return (Criteria) this;
        }

        public Criteria andFileFormatLike(String value) {
            addCriterion("file_format like", value, "fileFormat");
            return (Criteria) this;
        }

        public Criteria andFileFormatNotLike(String value) {
            addCriterion("file_format not like", value, "fileFormat");
            return (Criteria) this;
        }

        public Criteria andFileFormatIn(List<String> values) {
            addCriterion("file_format in", values, "fileFormat");
            return (Criteria) this;
        }

        public Criteria andFileFormatNotIn(List<String> values) {
            addCriterion("file_format not in", values, "fileFormat");
            return (Criteria) this;
        }

        public Criteria andFileFormatBetween(String value1, String value2) {
            addCriterion("file_format between", value1, value2, "fileFormat");
            return (Criteria) this;
        }

        public Criteria andFileFormatNotBetween(String value1, String value2) {
            addCriterion("file_format not between", value1, value2, "fileFormat");
            return (Criteria) this;
        }

        public Criteria andColNumIsNull() {
            addCriterion("col_num is null");
            return (Criteria) this;
        }

        public Criteria andColNumIsNotNull() {
            addCriterion("col_num is not null");
            return (Criteria) this;
        }

        public Criteria andColNumEqualTo(Long value) {
            addCriterion("col_num =", value, "colNum");
            return (Criteria) this;
        }

        public Criteria andColNumNotEqualTo(Long value) {
            addCriterion("col_num <>", value, "colNum");
            return (Criteria) this;
        }

        public Criteria andColNumGreaterThan(Long value) {
            addCriterion("col_num >", value, "colNum");
            return (Criteria) this;
        }

        public Criteria andColNumGreaterThanOrEqualTo(Long value) {
            addCriterion("col_num >=", value, "colNum");
            return (Criteria) this;
        }

        public Criteria andColNumLessThan(Long value) {
            addCriterion("col_num <", value, "colNum");
            return (Criteria) this;
        }

        public Criteria andColNumLessThanOrEqualTo(Long value) {
            addCriterion("col_num <=", value, "colNum");
            return (Criteria) this;
        }

        public Criteria andColNumIn(List<Long> values) {
            addCriterion("col_num in", values, "colNum");
            return (Criteria) this;
        }

        public Criteria andColNumNotIn(List<Long> values) {
            addCriterion("col_num not in", values, "colNum");
            return (Criteria) this;
        }

        public Criteria andColNumBetween(Long value1, Long value2) {
            addCriterion("col_num between", value1, value2, "colNum");
            return (Criteria) this;
        }

        public Criteria andColNumNotBetween(Long value1, Long value2) {
            addCriterion("col_num not between", value1, value2, "colNum");
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