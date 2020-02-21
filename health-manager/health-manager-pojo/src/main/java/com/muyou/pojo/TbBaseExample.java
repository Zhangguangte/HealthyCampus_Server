package com.muyou.pojo;

import java.util.ArrayList;
import java.util.List;

public class TbBaseExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TbBaseExample() {
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

        public Criteria andWebNameIsNull() {
            addCriterion("webName is null");
            return (Criteria) this;
        }

        public Criteria andWebNameIsNotNull() {
            addCriterion("webName is not null");
            return (Criteria) this;
        }

        public Criteria andWebNameEqualTo(String value) {
            addCriterion("webName =", value, "webName");
            return (Criteria) this;
        }

        public Criteria andWebNameNotEqualTo(String value) {
            addCriterion("webName <>", value, "webName");
            return (Criteria) this;
        }

        public Criteria andWebNameGreaterThan(String value) {
            addCriterion("webName >", value, "webName");
            return (Criteria) this;
        }

        public Criteria andWebNameGreaterThanOrEqualTo(String value) {
            addCriterion("webName >=", value, "webName");
            return (Criteria) this;
        }

        public Criteria andWebNameLessThan(String value) {
            addCriterion("webName <", value, "webName");
            return (Criteria) this;
        }

        public Criteria andWebNameLessThanOrEqualTo(String value) {
            addCriterion("webName <=", value, "webName");
            return (Criteria) this;
        }

        public Criteria andWebNameLike(String value) {
            addCriterion("webName like", value, "webName");
            return (Criteria) this;
        }

        public Criteria andWebNameNotLike(String value) {
            addCriterion("webName not like", value, "webName");
            return (Criteria) this;
        }

        public Criteria andWebNameIn(List<String> values) {
            addCriterion("webName in", values, "webName");
            return (Criteria) this;
        }

        public Criteria andWebNameNotIn(List<String> values) {
            addCriterion("webName not in", values, "webName");
            return (Criteria) this;
        }

        public Criteria andWebNameBetween(String value1, String value2) {
            addCriterion("webName between", value1, value2, "webName");
            return (Criteria) this;
        }

        public Criteria andWebNameNotBetween(String value1, String value2) {
            addCriterion("webName not between", value1, value2, "webName");
            return (Criteria) this;
        }

        public Criteria andKeyWordIsNull() {
            addCriterion("keyWord is null");
            return (Criteria) this;
        }

        public Criteria andKeyWordIsNotNull() {
            addCriterion("keyWord is not null");
            return (Criteria) this;
        }

        public Criteria andKeyWordEqualTo(String value) {
            addCriterion("keyWord =", value, "keyWord");
            return (Criteria) this;
        }

        public Criteria andKeyWordNotEqualTo(String value) {
            addCriterion("keyWord <>", value, "keyWord");
            return (Criteria) this;
        }

        public Criteria andKeyWordGreaterThan(String value) {
            addCriterion("keyWord >", value, "keyWord");
            return (Criteria) this;
        }

        public Criteria andKeyWordGreaterThanOrEqualTo(String value) {
            addCriterion("keyWord >=", value, "keyWord");
            return (Criteria) this;
        }

        public Criteria andKeyWordLessThan(String value) {
            addCriterion("keyWord <", value, "keyWord");
            return (Criteria) this;
        }

        public Criteria andKeyWordLessThanOrEqualTo(String value) {
            addCriterion("keyWord <=", value, "keyWord");
            return (Criteria) this;
        }

        public Criteria andKeyWordLike(String value) {
            addCriterion("keyWord like", value, "keyWord");
            return (Criteria) this;
        }

        public Criteria andKeyWordNotLike(String value) {
            addCriterion("keyWord not like", value, "keyWord");
            return (Criteria) this;
        }

        public Criteria andKeyWordIn(List<String> values) {
            addCriterion("keyWord in", values, "keyWord");
            return (Criteria) this;
        }

        public Criteria andKeyWordNotIn(List<String> values) {
            addCriterion("keyWord not in", values, "keyWord");
            return (Criteria) this;
        }

        public Criteria andKeyWordBetween(String value1, String value2) {
            addCriterion("keyWord between", value1, value2, "keyWord");
            return (Criteria) this;
        }

        public Criteria andKeyWordNotBetween(String value1, String value2) {
            addCriterion("keyWord not between", value1, value2, "keyWord");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNull() {
            addCriterion("description is null");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNotNull() {
            addCriterion("description is not null");
            return (Criteria) this;
        }

        public Criteria andDescriptionEqualTo(String value) {
            addCriterion("description =", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotEqualTo(String value) {
            addCriterion("description <>", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThan(String value) {
            addCriterion("description >", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("description >=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThan(String value) {
            addCriterion("description <", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThanOrEqualTo(String value) {
            addCriterion("description <=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLike(String value) {
            addCriterion("description like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotLike(String value) {
            addCriterion("description not like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionIn(List<String> values) {
            addCriterion("description in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotIn(List<String> values) {
            addCriterion("description not in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionBetween(String value1, String value2) {
            addCriterion("description between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotBetween(String value1, String value2) {
            addCriterion("description not between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andSourcePathIsNull() {
            addCriterion("sourcePath is null");
            return (Criteria) this;
        }

        public Criteria andSourcePathIsNotNull() {
            addCriterion("sourcePath is not null");
            return (Criteria) this;
        }

        public Criteria andSourcePathEqualTo(String value) {
            addCriterion("sourcePath =", value, "sourcePath");
            return (Criteria) this;
        }

        public Criteria andSourcePathNotEqualTo(String value) {
            addCriterion("sourcePath <>", value, "sourcePath");
            return (Criteria) this;
        }

        public Criteria andSourcePathGreaterThan(String value) {
            addCriterion("sourcePath >", value, "sourcePath");
            return (Criteria) this;
        }

        public Criteria andSourcePathGreaterThanOrEqualTo(String value) {
            addCriterion("sourcePath >=", value, "sourcePath");
            return (Criteria) this;
        }

        public Criteria andSourcePathLessThan(String value) {
            addCriterion("sourcePath <", value, "sourcePath");
            return (Criteria) this;
        }

        public Criteria andSourcePathLessThanOrEqualTo(String value) {
            addCriterion("sourcePath <=", value, "sourcePath");
            return (Criteria) this;
        }

        public Criteria andSourcePathLike(String value) {
            addCriterion("sourcePath like", value, "sourcePath");
            return (Criteria) this;
        }

        public Criteria andSourcePathNotLike(String value) {
            addCriterion("sourcePath not like", value, "sourcePath");
            return (Criteria) this;
        }

        public Criteria andSourcePathIn(List<String> values) {
            addCriterion("sourcePath in", values, "sourcePath");
            return (Criteria) this;
        }

        public Criteria andSourcePathNotIn(List<String> values) {
            addCriterion("sourcePath not in", values, "sourcePath");
            return (Criteria) this;
        }

        public Criteria andSourcePathBetween(String value1, String value2) {
            addCriterion("sourcePath between", value1, value2, "sourcePath");
            return (Criteria) this;
        }

        public Criteria andSourcePathNotBetween(String value1, String value2) {
            addCriterion("sourcePath not between", value1, value2, "sourcePath");
            return (Criteria) this;
        }

        public Criteria andUploadPathIsNull() {
            addCriterion("uploadPath is null");
            return (Criteria) this;
        }

        public Criteria andUploadPathIsNotNull() {
            addCriterion("uploadPath is not null");
            return (Criteria) this;
        }

        public Criteria andUploadPathEqualTo(String value) {
            addCriterion("uploadPath =", value, "uploadPath");
            return (Criteria) this;
        }

        public Criteria andUploadPathNotEqualTo(String value) {
            addCriterion("uploadPath <>", value, "uploadPath");
            return (Criteria) this;
        }

        public Criteria andUploadPathGreaterThan(String value) {
            addCriterion("uploadPath >", value, "uploadPath");
            return (Criteria) this;
        }

        public Criteria andUploadPathGreaterThanOrEqualTo(String value) {
            addCriterion("uploadPath >=", value, "uploadPath");
            return (Criteria) this;
        }

        public Criteria andUploadPathLessThan(String value) {
            addCriterion("uploadPath <", value, "uploadPath");
            return (Criteria) this;
        }

        public Criteria andUploadPathLessThanOrEqualTo(String value) {
            addCriterion("uploadPath <=", value, "uploadPath");
            return (Criteria) this;
        }

        public Criteria andUploadPathLike(String value) {
            addCriterion("uploadPath like", value, "uploadPath");
            return (Criteria) this;
        }

        public Criteria andUploadPathNotLike(String value) {
            addCriterion("uploadPath not like", value, "uploadPath");
            return (Criteria) this;
        }

        public Criteria andUploadPathIn(List<String> values) {
            addCriterion("uploadPath in", values, "uploadPath");
            return (Criteria) this;
        }

        public Criteria andUploadPathNotIn(List<String> values) {
            addCriterion("uploadPath not in", values, "uploadPath");
            return (Criteria) this;
        }

        public Criteria andUploadPathBetween(String value1, String value2) {
            addCriterion("uploadPath between", value1, value2, "uploadPath");
            return (Criteria) this;
        }

        public Criteria andUploadPathNotBetween(String value1, String value2) {
            addCriterion("uploadPath not between", value1, value2, "uploadPath");
            return (Criteria) this;
        }

        public Criteria andCopyrightIsNull() {
            addCriterion("copyright is null");
            return (Criteria) this;
        }

        public Criteria andCopyrightIsNotNull() {
            addCriterion("copyright is not null");
            return (Criteria) this;
        }

        public Criteria andCopyrightEqualTo(String value) {
            addCriterion("copyright =", value, "copyright");
            return (Criteria) this;
        }

        public Criteria andCopyrightNotEqualTo(String value) {
            addCriterion("copyright <>", value, "copyright");
            return (Criteria) this;
        }

        public Criteria andCopyrightGreaterThan(String value) {
            addCriterion("copyright >", value, "copyright");
            return (Criteria) this;
        }

        public Criteria andCopyrightGreaterThanOrEqualTo(String value) {
            addCriterion("copyright >=", value, "copyright");
            return (Criteria) this;
        }

        public Criteria andCopyrightLessThan(String value) {
            addCriterion("copyright <", value, "copyright");
            return (Criteria) this;
        }

        public Criteria andCopyrightLessThanOrEqualTo(String value) {
            addCriterion("copyright <=", value, "copyright");
            return (Criteria) this;
        }

        public Criteria andCopyrightLike(String value) {
            addCriterion("copyright like", value, "copyright");
            return (Criteria) this;
        }

        public Criteria andCopyrightNotLike(String value) {
            addCriterion("copyright not like", value, "copyright");
            return (Criteria) this;
        }

        public Criteria andCopyrightIn(List<String> values) {
            addCriterion("copyright in", values, "copyright");
            return (Criteria) this;
        }

        public Criteria andCopyrightNotIn(List<String> values) {
            addCriterion("copyright not in", values, "copyright");
            return (Criteria) this;
        }

        public Criteria andCopyrightBetween(String value1, String value2) {
            addCriterion("copyright between", value1, value2, "copyright");
            return (Criteria) this;
        }

        public Criteria andCopyrightNotBetween(String value1, String value2) {
            addCriterion("copyright not between", value1, value2, "copyright");
            return (Criteria) this;
        }

        public Criteria andCountCodeIsNull() {
            addCriterion("countCode is null");
            return (Criteria) this;
        }

        public Criteria andCountCodeIsNotNull() {
            addCriterion("countCode is not null");
            return (Criteria) this;
        }

        public Criteria andCountCodeEqualTo(String value) {
            addCriterion("countCode =", value, "countCode");
            return (Criteria) this;
        }

        public Criteria andCountCodeNotEqualTo(String value) {
            addCriterion("countCode <>", value, "countCode");
            return (Criteria) this;
        }

        public Criteria andCountCodeGreaterThan(String value) {
            addCriterion("countCode >", value, "countCode");
            return (Criteria) this;
        }

        public Criteria andCountCodeGreaterThanOrEqualTo(String value) {
            addCriterion("countCode >=", value, "countCode");
            return (Criteria) this;
        }

        public Criteria andCountCodeLessThan(String value) {
            addCriterion("countCode <", value, "countCode");
            return (Criteria) this;
        }

        public Criteria andCountCodeLessThanOrEqualTo(String value) {
            addCriterion("countCode <=", value, "countCode");
            return (Criteria) this;
        }

        public Criteria andCountCodeLike(String value) {
            addCriterion("countCode like", value, "countCode");
            return (Criteria) this;
        }

        public Criteria andCountCodeNotLike(String value) {
            addCriterion("countCode not like", value, "countCode");
            return (Criteria) this;
        }

        public Criteria andCountCodeIn(List<String> values) {
            addCriterion("countCode in", values, "countCode");
            return (Criteria) this;
        }

        public Criteria andCountCodeNotIn(List<String> values) {
            addCriterion("countCode not in", values, "countCode");
            return (Criteria) this;
        }

        public Criteria andCountCodeBetween(String value1, String value2) {
            addCriterion("countCode between", value1, value2, "countCode");
            return (Criteria) this;
        }

        public Criteria andCountCodeNotBetween(String value1, String value2) {
            addCriterion("countCode not between", value1, value2, "countCode");
            return (Criteria) this;
        }

        public Criteria andHasLogNoticeIsNull() {
            addCriterion("hasLogNotice is null");
            return (Criteria) this;
        }

        public Criteria andHasLogNoticeIsNotNull() {
            addCriterion("hasLogNotice is not null");
            return (Criteria) this;
        }

        public Criteria andHasLogNoticeEqualTo(Integer value) {
            addCriterion("hasLogNotice =", value, "hasLogNotice");
            return (Criteria) this;
        }

        public Criteria andHasLogNoticeNotEqualTo(Integer value) {
            addCriterion("hasLogNotice <>", value, "hasLogNotice");
            return (Criteria) this;
        }

        public Criteria andHasLogNoticeGreaterThan(Integer value) {
            addCriterion("hasLogNotice >", value, "hasLogNotice");
            return (Criteria) this;
        }

        public Criteria andHasLogNoticeGreaterThanOrEqualTo(Integer value) {
            addCriterion("hasLogNotice >=", value, "hasLogNotice");
            return (Criteria) this;
        }

        public Criteria andHasLogNoticeLessThan(Integer value) {
            addCriterion("hasLogNotice <", value, "hasLogNotice");
            return (Criteria) this;
        }

        public Criteria andHasLogNoticeLessThanOrEqualTo(Integer value) {
            addCriterion("hasLogNotice <=", value, "hasLogNotice");
            return (Criteria) this;
        }

        public Criteria andHasLogNoticeIn(List<Integer> values) {
            addCriterion("hasLogNotice in", values, "hasLogNotice");
            return (Criteria) this;
        }

        public Criteria andHasLogNoticeNotIn(List<Integer> values) {
            addCriterion("hasLogNotice not in", values, "hasLogNotice");
            return (Criteria) this;
        }

        public Criteria andHasLogNoticeBetween(Integer value1, Integer value2) {
            addCriterion("hasLogNotice between", value1, value2, "hasLogNotice");
            return (Criteria) this;
        }

        public Criteria andHasLogNoticeNotBetween(Integer value1, Integer value2) {
            addCriterion("hasLogNotice not between", value1, value2, "hasLogNotice");
            return (Criteria) this;
        }

        public Criteria andLogNoticeIsNull() {
            addCriterion("logNotice is null");
            return (Criteria) this;
        }

        public Criteria andLogNoticeIsNotNull() {
            addCriterion("logNotice is not null");
            return (Criteria) this;
        }

        public Criteria andLogNoticeEqualTo(String value) {
            addCriterion("logNotice =", value, "logNotice");
            return (Criteria) this;
        }

        public Criteria andLogNoticeNotEqualTo(String value) {
            addCriterion("logNotice <>", value, "logNotice");
            return (Criteria) this;
        }

        public Criteria andLogNoticeGreaterThan(String value) {
            addCriterion("logNotice >", value, "logNotice");
            return (Criteria) this;
        }

        public Criteria andLogNoticeGreaterThanOrEqualTo(String value) {
            addCriterion("logNotice >=", value, "logNotice");
            return (Criteria) this;
        }

        public Criteria andLogNoticeLessThan(String value) {
            addCriterion("logNotice <", value, "logNotice");
            return (Criteria) this;
        }

        public Criteria andLogNoticeLessThanOrEqualTo(String value) {
            addCriterion("logNotice <=", value, "logNotice");
            return (Criteria) this;
        }

        public Criteria andLogNoticeLike(String value) {
            addCriterion("logNotice like", value, "logNotice");
            return (Criteria) this;
        }

        public Criteria andLogNoticeNotLike(String value) {
            addCriterion("logNotice not like", value, "logNotice");
            return (Criteria) this;
        }

        public Criteria andLogNoticeIn(List<String> values) {
            addCriterion("logNotice in", values, "logNotice");
            return (Criteria) this;
        }

        public Criteria andLogNoticeNotIn(List<String> values) {
            addCriterion("logNotice not in", values, "logNotice");
            return (Criteria) this;
        }

        public Criteria andLogNoticeBetween(String value1, String value2) {
            addCriterion("logNotice between", value1, value2, "logNotice");
            return (Criteria) this;
        }

        public Criteria andLogNoticeNotBetween(String value1, String value2) {
            addCriterion("logNotice not between", value1, value2, "logNotice");
            return (Criteria) this;
        }

        public Criteria andHasAllNoticeIsNull() {
            addCriterion("hasAllNotice is null");
            return (Criteria) this;
        }

        public Criteria andHasAllNoticeIsNotNull() {
            addCriterion("hasAllNotice is not null");
            return (Criteria) this;
        }

        public Criteria andHasAllNoticeEqualTo(Integer value) {
            addCriterion("hasAllNotice =", value, "hasAllNotice");
            return (Criteria) this;
        }

        public Criteria andHasAllNoticeNotEqualTo(Integer value) {
            addCriterion("hasAllNotice <>", value, "hasAllNotice");
            return (Criteria) this;
        }

        public Criteria andHasAllNoticeGreaterThan(Integer value) {
            addCriterion("hasAllNotice >", value, "hasAllNotice");
            return (Criteria) this;
        }

        public Criteria andHasAllNoticeGreaterThanOrEqualTo(Integer value) {
            addCriterion("hasAllNotice >=", value, "hasAllNotice");
            return (Criteria) this;
        }

        public Criteria andHasAllNoticeLessThan(Integer value) {
            addCriterion("hasAllNotice <", value, "hasAllNotice");
            return (Criteria) this;
        }

        public Criteria andHasAllNoticeLessThanOrEqualTo(Integer value) {
            addCriterion("hasAllNotice <=", value, "hasAllNotice");
            return (Criteria) this;
        }

        public Criteria andHasAllNoticeIn(List<Integer> values) {
            addCriterion("hasAllNotice in", values, "hasAllNotice");
            return (Criteria) this;
        }

        public Criteria andHasAllNoticeNotIn(List<Integer> values) {
            addCriterion("hasAllNotice not in", values, "hasAllNotice");
            return (Criteria) this;
        }

        public Criteria andHasAllNoticeBetween(Integer value1, Integer value2) {
            addCriterion("hasAllNotice between", value1, value2, "hasAllNotice");
            return (Criteria) this;
        }

        public Criteria andHasAllNoticeNotBetween(Integer value1, Integer value2) {
            addCriterion("hasAllNotice not between", value1, value2, "hasAllNotice");
            return (Criteria) this;
        }

        public Criteria andAllNoticeIsNull() {
            addCriterion("allNotice is null");
            return (Criteria) this;
        }

        public Criteria andAllNoticeIsNotNull() {
            addCriterion("allNotice is not null");
            return (Criteria) this;
        }

        public Criteria andAllNoticeEqualTo(String value) {
            addCriterion("allNotice =", value, "allNotice");
            return (Criteria) this;
        }

        public Criteria andAllNoticeNotEqualTo(String value) {
            addCriterion("allNotice <>", value, "allNotice");
            return (Criteria) this;
        }

        public Criteria andAllNoticeGreaterThan(String value) {
            addCriterion("allNotice >", value, "allNotice");
            return (Criteria) this;
        }

        public Criteria andAllNoticeGreaterThanOrEqualTo(String value) {
            addCriterion("allNotice >=", value, "allNotice");
            return (Criteria) this;
        }

        public Criteria andAllNoticeLessThan(String value) {
            addCriterion("allNotice <", value, "allNotice");
            return (Criteria) this;
        }

        public Criteria andAllNoticeLessThanOrEqualTo(String value) {
            addCriterion("allNotice <=", value, "allNotice");
            return (Criteria) this;
        }

        public Criteria andAllNoticeLike(String value) {
            addCriterion("allNotice like", value, "allNotice");
            return (Criteria) this;
        }

        public Criteria andAllNoticeNotLike(String value) {
            addCriterion("allNotice not like", value, "allNotice");
            return (Criteria) this;
        }

        public Criteria andAllNoticeIn(List<String> values) {
            addCriterion("allNotice in", values, "allNotice");
            return (Criteria) this;
        }

        public Criteria andAllNoticeNotIn(List<String> values) {
            addCriterion("allNotice not in", values, "allNotice");
            return (Criteria) this;
        }

        public Criteria andAllNoticeBetween(String value1, String value2) {
            addCriterion("allNotice between", value1, value2, "allNotice");
            return (Criteria) this;
        }

        public Criteria andAllNoticeNotBetween(String value1, String value2) {
            addCriterion("allNotice not between", value1, value2, "allNotice");
            return (Criteria) this;
        }

        public Criteria andNoticeIsNull() {
            addCriterion("notice is null");
            return (Criteria) this;
        }

        public Criteria andNoticeIsNotNull() {
            addCriterion("notice is not null");
            return (Criteria) this;
        }

        public Criteria andNoticeEqualTo(String value) {
            addCriterion("notice =", value, "notice");
            return (Criteria) this;
        }

        public Criteria andNoticeNotEqualTo(String value) {
            addCriterion("notice <>", value, "notice");
            return (Criteria) this;
        }

        public Criteria andNoticeGreaterThan(String value) {
            addCriterion("notice >", value, "notice");
            return (Criteria) this;
        }

        public Criteria andNoticeGreaterThanOrEqualTo(String value) {
            addCriterion("notice >=", value, "notice");
            return (Criteria) this;
        }

        public Criteria andNoticeLessThan(String value) {
            addCriterion("notice <", value, "notice");
            return (Criteria) this;
        }

        public Criteria andNoticeLessThanOrEqualTo(String value) {
            addCriterion("notice <=", value, "notice");
            return (Criteria) this;
        }

        public Criteria andNoticeLike(String value) {
            addCriterion("notice like", value, "notice");
            return (Criteria) this;
        }

        public Criteria andNoticeNotLike(String value) {
            addCriterion("notice not like", value, "notice");
            return (Criteria) this;
        }

        public Criteria andNoticeIn(List<String> values) {
            addCriterion("notice in", values, "notice");
            return (Criteria) this;
        }

        public Criteria andNoticeNotIn(List<String> values) {
            addCriterion("notice not in", values, "notice");
            return (Criteria) this;
        }

        public Criteria andNoticeBetween(String value1, String value2) {
            addCriterion("notice between", value1, value2, "notice");
            return (Criteria) this;
        }

        public Criteria andNoticeNotBetween(String value1, String value2) {
            addCriterion("notice not between", value1, value2, "notice");
            return (Criteria) this;
        }

        public Criteria andUpdateLogIsNull() {
            addCriterion("updateLog is null");
            return (Criteria) this;
        }

        public Criteria andUpdateLogIsNotNull() {
            addCriterion("updateLog is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateLogEqualTo(String value) {
            addCriterion("updateLog =", value, "updateLog");
            return (Criteria) this;
        }

        public Criteria andUpdateLogNotEqualTo(String value) {
            addCriterion("updateLog <>", value, "updateLog");
            return (Criteria) this;
        }

        public Criteria andUpdateLogGreaterThan(String value) {
            addCriterion("updateLog >", value, "updateLog");
            return (Criteria) this;
        }

        public Criteria andUpdateLogGreaterThanOrEqualTo(String value) {
            addCriterion("updateLog >=", value, "updateLog");
            return (Criteria) this;
        }

        public Criteria andUpdateLogLessThan(String value) {
            addCriterion("updateLog <", value, "updateLog");
            return (Criteria) this;
        }

        public Criteria andUpdateLogLessThanOrEqualTo(String value) {
            addCriterion("updateLog <=", value, "updateLog");
            return (Criteria) this;
        }

        public Criteria andUpdateLogLike(String value) {
            addCriterion("updateLog like", value, "updateLog");
            return (Criteria) this;
        }

        public Criteria andUpdateLogNotLike(String value) {
            addCriterion("updateLog not like", value, "updateLog");
            return (Criteria) this;
        }

        public Criteria andUpdateLogIn(List<String> values) {
            addCriterion("updateLog in", values, "updateLog");
            return (Criteria) this;
        }

        public Criteria andUpdateLogNotIn(List<String> values) {
            addCriterion("updateLog not in", values, "updateLog");
            return (Criteria) this;
        }

        public Criteria andUpdateLogBetween(String value1, String value2) {
            addCriterion("updateLog between", value1, value2, "updateLog");
            return (Criteria) this;
        }

        public Criteria andUpdateLogNotBetween(String value1, String value2) {
            addCriterion("updateLog not between", value1, value2, "updateLog");
            return (Criteria) this;
        }

        public Criteria andFrontUrlIsNull() {
            addCriterion("frontUrl is null");
            return (Criteria) this;
        }

        public Criteria andFrontUrlIsNotNull() {
            addCriterion("frontUrl is not null");
            return (Criteria) this;
        }

        public Criteria andFrontUrlEqualTo(String value) {
            addCriterion("frontUrl =", value, "frontUrl");
            return (Criteria) this;
        }

        public Criteria andFrontUrlNotEqualTo(String value) {
            addCriterion("frontUrl <>", value, "frontUrl");
            return (Criteria) this;
        }

        public Criteria andFrontUrlGreaterThan(String value) {
            addCriterion("frontUrl >", value, "frontUrl");
            return (Criteria) this;
        }

        public Criteria andFrontUrlGreaterThanOrEqualTo(String value) {
            addCriterion("frontUrl >=", value, "frontUrl");
            return (Criteria) this;
        }

        public Criteria andFrontUrlLessThan(String value) {
            addCriterion("frontUrl <", value, "frontUrl");
            return (Criteria) this;
        }

        public Criteria andFrontUrlLessThanOrEqualTo(String value) {
            addCriterion("frontUrl <=", value, "frontUrl");
            return (Criteria) this;
        }

        public Criteria andFrontUrlLike(String value) {
            addCriterion("frontUrl like", value, "frontUrl");
            return (Criteria) this;
        }

        public Criteria andFrontUrlNotLike(String value) {
            addCriterion("frontUrl not like", value, "frontUrl");
            return (Criteria) this;
        }

        public Criteria andFrontUrlIn(List<String> values) {
            addCriterion("frontUrl in", values, "frontUrl");
            return (Criteria) this;
        }

        public Criteria andFrontUrlNotIn(List<String> values) {
            addCriterion("frontUrl not in", values, "frontUrl");
            return (Criteria) this;
        }

        public Criteria andFrontUrlBetween(String value1, String value2) {
            addCriterion("frontUrl between", value1, value2, "frontUrl");
            return (Criteria) this;
        }

        public Criteria andFrontUrlNotBetween(String value1, String value2) {
            addCriterion("frontUrl not between", value1, value2, "frontUrl");
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