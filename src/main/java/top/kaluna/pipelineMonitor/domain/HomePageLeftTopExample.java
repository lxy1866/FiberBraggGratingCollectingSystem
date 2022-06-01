package top.kaluna.pipelineMonitor.domain;

import java.util.ArrayList;
import java.util.List;

public class HomePageLeftTopExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public HomePageLeftTopExample() {
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

        public Criteria andFrequencyIsNull() {
            addCriterion("frequency is null");
            return (Criteria) this;
        }

        public Criteria andFrequencyIsNotNull() {
            addCriterion("frequency is not null");
            return (Criteria) this;
        }

        public Criteria andFrequencyEqualTo(String value) {
            addCriterion("frequency =", value, "frequency");
            return (Criteria) this;
        }

        public Criteria andFrequencyNotEqualTo(String value) {
            addCriterion("frequency <>", value, "frequency");
            return (Criteria) this;
        }

        public Criteria andFrequencyGreaterThan(String value) {
            addCriterion("frequency >", value, "frequency");
            return (Criteria) this;
        }

        public Criteria andFrequencyGreaterThanOrEqualTo(String value) {
            addCriterion("frequency >=", value, "frequency");
            return (Criteria) this;
        }

        public Criteria andFrequencyLessThan(String value) {
            addCriterion("frequency <", value, "frequency");
            return (Criteria) this;
        }

        public Criteria andFrequencyLessThanOrEqualTo(String value) {
            addCriterion("frequency <=", value, "frequency");
            return (Criteria) this;
        }

        public Criteria andFrequencyLike(String value) {
            addCriterion("frequency like", value, "frequency");
            return (Criteria) this;
        }

        public Criteria andFrequencyNotLike(String value) {
            addCriterion("frequency not like", value, "frequency");
            return (Criteria) this;
        }

        public Criteria andFrequencyIn(List<String> values) {
            addCriterion("frequency in", values, "frequency");
            return (Criteria) this;
        }

        public Criteria andFrequencyNotIn(List<String> values) {
            addCriterion("frequency not in", values, "frequency");
            return (Criteria) this;
        }

        public Criteria andFrequencyBetween(String value1, String value2) {
            addCriterion("frequency between", value1, value2, "frequency");
            return (Criteria) this;
        }

        public Criteria andFrequencyNotBetween(String value1, String value2) {
            addCriterion("frequency not between", value1, value2, "frequency");
            return (Criteria) this;
        }

        public Criteria andArrayTotalIsNull() {
            addCriterion("array_total is null");
            return (Criteria) this;
        }

        public Criteria andArrayTotalIsNotNull() {
            addCriterion("array_total is not null");
            return (Criteria) this;
        }

        public Criteria andArrayTotalEqualTo(Long value) {
            addCriterion("array_total =", value, "arrayTotal");
            return (Criteria) this;
        }

        public Criteria andArrayTotalNotEqualTo(Long value) {
            addCriterion("array_total <>", value, "arrayTotal");
            return (Criteria) this;
        }

        public Criteria andArrayTotalGreaterThan(Long value) {
            addCriterion("array_total >", value, "arrayTotal");
            return (Criteria) this;
        }

        public Criteria andArrayTotalGreaterThanOrEqualTo(Long value) {
            addCriterion("array_total >=", value, "arrayTotal");
            return (Criteria) this;
        }

        public Criteria andArrayTotalLessThan(Long value) {
            addCriterion("array_total <", value, "arrayTotal");
            return (Criteria) this;
        }

        public Criteria andArrayTotalLessThanOrEqualTo(Long value) {
            addCriterion("array_total <=", value, "arrayTotal");
            return (Criteria) this;
        }

        public Criteria andArrayTotalIn(List<Long> values) {
            addCriterion("array_total in", values, "arrayTotal");
            return (Criteria) this;
        }

        public Criteria andArrayTotalNotIn(List<Long> values) {
            addCriterion("array_total not in", values, "arrayTotal");
            return (Criteria) this;
        }

        public Criteria andArrayTotalBetween(Long value1, Long value2) {
            addCriterion("array_total between", value1, value2, "arrayTotal");
            return (Criteria) this;
        }

        public Criteria andArrayTotalNotBetween(Long value1, Long value2) {
            addCriterion("array_total not between", value1, value2, "arrayTotal");
            return (Criteria) this;
        }

        public Criteria andEachArrayNumIsNull() {
            addCriterion("each_array_num is null");
            return (Criteria) this;
        }

        public Criteria andEachArrayNumIsNotNull() {
            addCriterion("each_array_num is not null");
            return (Criteria) this;
        }

        public Criteria andEachArrayNumEqualTo(String value) {
            addCriterion("each_array_num =", value, "eachArrayNum");
            return (Criteria) this;
        }

        public Criteria andEachArrayNumNotEqualTo(String value) {
            addCriterion("each_array_num <>", value, "eachArrayNum");
            return (Criteria) this;
        }

        public Criteria andEachArrayNumGreaterThan(String value) {
            addCriterion("each_array_num >", value, "eachArrayNum");
            return (Criteria) this;
        }

        public Criteria andEachArrayNumGreaterThanOrEqualTo(String value) {
            addCriterion("each_array_num >=", value, "eachArrayNum");
            return (Criteria) this;
        }

        public Criteria andEachArrayNumLessThan(String value) {
            addCriterion("each_array_num <", value, "eachArrayNum");
            return (Criteria) this;
        }

        public Criteria andEachArrayNumLessThanOrEqualTo(String value) {
            addCriterion("each_array_num <=", value, "eachArrayNum");
            return (Criteria) this;
        }

        public Criteria andEachArrayNumLike(String value) {
            addCriterion("each_array_num like", value, "eachArrayNum");
            return (Criteria) this;
        }

        public Criteria andEachArrayNumNotLike(String value) {
            addCriterion("each_array_num not like", value, "eachArrayNum");
            return (Criteria) this;
        }

        public Criteria andEachArrayNumIn(List<String> values) {
            addCriterion("each_array_num in", values, "eachArrayNum");
            return (Criteria) this;
        }

        public Criteria andEachArrayNumNotIn(List<String> values) {
            addCriterion("each_array_num not in", values, "eachArrayNum");
            return (Criteria) this;
        }

        public Criteria andEachArrayNumBetween(String value1, String value2) {
            addCriterion("each_array_num between", value1, value2, "eachArrayNum");
            return (Criteria) this;
        }

        public Criteria andEachArrayNumNotBetween(String value1, String value2) {
            addCriterion("each_array_num not between", value1, value2, "eachArrayNum");
            return (Criteria) this;
        }

        public Criteria andGroupNumAmIsNull() {
            addCriterion("group_num_am is null");
            return (Criteria) this;
        }

        public Criteria andGroupNumAmIsNotNull() {
            addCriterion("group_num_am is not null");
            return (Criteria) this;
        }

        public Criteria andGroupNumAmEqualTo(Long value) {
            addCriterion("group_num_am =", value, "groupNumAm");
            return (Criteria) this;
        }

        public Criteria andGroupNumAmNotEqualTo(Long value) {
            addCriterion("group_num_am <>", value, "groupNumAm");
            return (Criteria) this;
        }

        public Criteria andGroupNumAmGreaterThan(Long value) {
            addCriterion("group_num_am >", value, "groupNumAm");
            return (Criteria) this;
        }

        public Criteria andGroupNumAmGreaterThanOrEqualTo(Long value) {
            addCriterion("group_num_am >=", value, "groupNumAm");
            return (Criteria) this;
        }

        public Criteria andGroupNumAmLessThan(Long value) {
            addCriterion("group_num_am <", value, "groupNumAm");
            return (Criteria) this;
        }

        public Criteria andGroupNumAmLessThanOrEqualTo(Long value) {
            addCriterion("group_num_am <=", value, "groupNumAm");
            return (Criteria) this;
        }

        public Criteria andGroupNumAmIn(List<Long> values) {
            addCriterion("group_num_am in", values, "groupNumAm");
            return (Criteria) this;
        }

        public Criteria andGroupNumAmNotIn(List<Long> values) {
            addCriterion("group_num_am not in", values, "groupNumAm");
            return (Criteria) this;
        }

        public Criteria andGroupNumAmBetween(Long value1, Long value2) {
            addCriterion("group_num_am between", value1, value2, "groupNumAm");
            return (Criteria) this;
        }

        public Criteria andGroupNumAmNotBetween(Long value1, Long value2) {
            addCriterion("group_num_am not between", value1, value2, "groupNumAm");
            return (Criteria) this;
        }

        public Criteria andGroupNumPmIsNull() {
            addCriterion("group_num_pm is null");
            return (Criteria) this;
        }

        public Criteria andGroupNumPmIsNotNull() {
            addCriterion("group_num_pm is not null");
            return (Criteria) this;
        }

        public Criteria andGroupNumPmEqualTo(Long value) {
            addCriterion("group_num_pm =", value, "groupNumPm");
            return (Criteria) this;
        }

        public Criteria andGroupNumPmNotEqualTo(Long value) {
            addCriterion("group_num_pm <>", value, "groupNumPm");
            return (Criteria) this;
        }

        public Criteria andGroupNumPmGreaterThan(Long value) {
            addCriterion("group_num_pm >", value, "groupNumPm");
            return (Criteria) this;
        }

        public Criteria andGroupNumPmGreaterThanOrEqualTo(Long value) {
            addCriterion("group_num_pm >=", value, "groupNumPm");
            return (Criteria) this;
        }

        public Criteria andGroupNumPmLessThan(Long value) {
            addCriterion("group_num_pm <", value, "groupNumPm");
            return (Criteria) this;
        }

        public Criteria andGroupNumPmLessThanOrEqualTo(Long value) {
            addCriterion("group_num_pm <=", value, "groupNumPm");
            return (Criteria) this;
        }

        public Criteria andGroupNumPmIn(List<Long> values) {
            addCriterion("group_num_pm in", values, "groupNumPm");
            return (Criteria) this;
        }

        public Criteria andGroupNumPmNotIn(List<Long> values) {
            addCriterion("group_num_pm not in", values, "groupNumPm");
            return (Criteria) this;
        }

        public Criteria andGroupNumPmBetween(Long value1, Long value2) {
            addCriterion("group_num_pm between", value1, value2, "groupNumPm");
            return (Criteria) this;
        }

        public Criteria andGroupNumPmNotBetween(Long value1, Long value2) {
            addCriterion("group_num_pm not between", value1, value2, "groupNumPm");
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

        public Criteria andColNameIsNull() {
            addCriterion("col_name is null");
            return (Criteria) this;
        }

        public Criteria andColNameIsNotNull() {
            addCriterion("col_name is not null");
            return (Criteria) this;
        }

        public Criteria andColNameEqualTo(String value) {
            addCriterion("col_name =", value, "colName");
            return (Criteria) this;
        }

        public Criteria andColNameNotEqualTo(String value) {
            addCriterion("col_name <>", value, "colName");
            return (Criteria) this;
        }

        public Criteria andColNameGreaterThan(String value) {
            addCriterion("col_name >", value, "colName");
            return (Criteria) this;
        }

        public Criteria andColNameGreaterThanOrEqualTo(String value) {
            addCriterion("col_name >=", value, "colName");
            return (Criteria) this;
        }

        public Criteria andColNameLessThan(String value) {
            addCriterion("col_name <", value, "colName");
            return (Criteria) this;
        }

        public Criteria andColNameLessThanOrEqualTo(String value) {
            addCriterion("col_name <=", value, "colName");
            return (Criteria) this;
        }

        public Criteria andColNameLike(String value) {
            addCriterion("col_name like", value, "colName");
            return (Criteria) this;
        }

        public Criteria andColNameNotLike(String value) {
            addCriterion("col_name not like", value, "colName");
            return (Criteria) this;
        }

        public Criteria andColNameIn(List<String> values) {
            addCriterion("col_name in", values, "colName");
            return (Criteria) this;
        }

        public Criteria andColNameNotIn(List<String> values) {
            addCriterion("col_name not in", values, "colName");
            return (Criteria) this;
        }

        public Criteria andColNameBetween(String value1, String value2) {
            addCriterion("col_name between", value1, value2, "colName");
            return (Criteria) this;
        }

        public Criteria andColNameNotBetween(String value1, String value2) {
            addCriterion("col_name not between", value1, value2, "colName");
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