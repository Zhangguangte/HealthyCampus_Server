package com.muyou.pojo;

import java.util.ArrayList;
import java.util.List;

public class TbDiseaseExample {
    protected String orderByClause;

    protected int row;
    
    protected int size;
    
    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public TbDiseaseExample() {
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
        row = 0;
        size = 0;
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

        public Criteria andDiseaseNameIsNull() {
            addCriterion("disease_name is null");
            return (Criteria) this;
        }

        public Criteria andDiseaseNameIsNotNull() {
            addCriterion("disease_name is not null");
            return (Criteria) this;
        }

        public Criteria andDiseaseNameEqualTo(String value) {
            addCriterion("disease_name =", value, "diseaseName");
            return (Criteria) this;
        }

        public Criteria andDiseaseNameNotEqualTo(String value) {
            addCriterion("disease_name <>", value, "diseaseName");
            return (Criteria) this;
        }

        public Criteria andDiseaseNameGreaterThan(String value) {
            addCriterion("disease_name >", value, "diseaseName");
            return (Criteria) this;
        }

        public Criteria andDiseaseNameGreaterThanOrEqualTo(String value) {
            addCriterion("disease_name >=", value, "diseaseName");
            return (Criteria) this;
        }

        public Criteria andDiseaseNameLessThan(String value) {
            addCriterion("disease_name <", value, "diseaseName");
            return (Criteria) this;
        }

        public Criteria andDiseaseNameLessThanOrEqualTo(String value) {
            addCriterion("disease_name <=", value, "diseaseName");
            return (Criteria) this;
        }

        public Criteria andDiseaseNameLike(String value) {
            addCriterion("disease_name like", value, "diseaseName");
            return (Criteria) this;
        }

        public Criteria andDiseaseNameNotLike(String value) {
            addCriterion("disease_name not like", value, "diseaseName");
            return (Criteria) this;
        }

        public Criteria andDiseaseNameIn(List<String> values) {
            addCriterion("disease_name in", values, "diseaseName");
            return (Criteria) this;
        }

        public Criteria andDiseaseNameNotIn(List<String> values) {
            addCriterion("disease_name not in", values, "diseaseName");
            return (Criteria) this;
        }

        public Criteria andDiseaseNameBetween(String value1, String value2) {
            addCriterion("disease_name between", value1, value2, "diseaseName");
            return (Criteria) this;
        }

        public Criteria andDiseaseNameNotBetween(String value1, String value2) {
            addCriterion("disease_name not between", value1, value2, "diseaseName");
            return (Criteria) this;
        }

        public Criteria andDiseaseSymbolIsNull() {
            addCriterion("disease_symbol is null");
            return (Criteria) this;
        }

        public Criteria andDiseaseSymbolIsNotNull() {
            addCriterion("disease_symbol is not null");
            return (Criteria) this;
        }

        public Criteria andDiseaseSymbolEqualTo(String value) {
            addCriterion("disease_symbol =", value, "diseaseSymbol");
            return (Criteria) this;
        }

        public Criteria andDiseaseSymbolNotEqualTo(String value) {
            addCriterion("disease_symbol <>", value, "diseaseSymbol");
            return (Criteria) this;
        }

        public Criteria andDiseaseSymbolGreaterThan(String value) {
            addCriterion("disease_symbol >", value, "diseaseSymbol");
            return (Criteria) this;
        }

        public Criteria andDiseaseSymbolGreaterThanOrEqualTo(String value) {
            addCriterion("disease_symbol >=", value, "diseaseSymbol");
            return (Criteria) this;
        }

        public Criteria andDiseaseSymbolLessThan(String value) {
            addCriterion("disease_symbol <", value, "diseaseSymbol");
            return (Criteria) this;
        }

        public Criteria andDiseaseSymbolLessThanOrEqualTo(String value) {
            addCriterion("disease_symbol <=", value, "diseaseSymbol");
            return (Criteria) this;
        }

        public Criteria andDiseaseSymbolLike(String value) {
            addCriterion("disease_symbol like", value, "diseaseSymbol");
            return (Criteria) this;
        }

        public Criteria andDiseaseSymbolNotLike(String value) {
            addCriterion("disease_symbol not like", value, "diseaseSymbol");
            return (Criteria) this;
        }

        public Criteria andDiseaseSymbolIn(List<String> values) {
            addCriterion("disease_symbol in", values, "diseaseSymbol");
            return (Criteria) this;
        }

        public Criteria andDiseaseSymbolNotIn(List<String> values) {
            addCriterion("disease_symbol not in", values, "diseaseSymbol");
            return (Criteria) this;
        }

        public Criteria andDiseaseSymbolBetween(String value1, String value2) {
            addCriterion("disease_symbol between", value1, value2, "diseaseSymbol");
            return (Criteria) this;
        }

        public Criteria andDiseaseSymbolNotBetween(String value1, String value2) {
            addCriterion("disease_symbol not between", value1, value2, "diseaseSymbol");
            return (Criteria) this;
        }

        public Criteria andDiseaseAliasIsNull() {
            addCriterion("disease_alias is null");
            return (Criteria) this;
        }

        public Criteria andDiseaseAliasIsNotNull() {
            addCriterion("disease_alias is not null");
            return (Criteria) this;
        }

        public Criteria andDiseaseAliasEqualTo(String value) {
            addCriterion("disease_alias =", value, "diseaseAlias");
            return (Criteria) this;
        }

        public Criteria andDiseaseAliasNotEqualTo(String value) {
            addCriterion("disease_alias <>", value, "diseaseAlias");
            return (Criteria) this;
        }

        public Criteria andDiseaseAliasGreaterThan(String value) {
            addCriterion("disease_alias >", value, "diseaseAlias");
            return (Criteria) this;
        }

        public Criteria andDiseaseAliasGreaterThanOrEqualTo(String value) {
            addCriterion("disease_alias >=", value, "diseaseAlias");
            return (Criteria) this;
        }

        public Criteria andDiseaseAliasLessThan(String value) {
            addCriterion("disease_alias <", value, "diseaseAlias");
            return (Criteria) this;
        }

        public Criteria andDiseaseAliasLessThanOrEqualTo(String value) {
            addCriterion("disease_alias <=", value, "diseaseAlias");
            return (Criteria) this;
        }

        public Criteria andDiseaseAliasLike(String value) {
            addCriterion("disease_alias like", value, "diseaseAlias");
            return (Criteria) this;
        }

        public Criteria andDiseaseAliasNotLike(String value) {
            addCriterion("disease_alias not like", value, "diseaseAlias");
            return (Criteria) this;
        }

        public Criteria andDiseaseAliasIn(List<String> values) {
            addCriterion("disease_alias in", values, "diseaseAlias");
            return (Criteria) this;
        }

        public Criteria andDiseaseAliasNotIn(List<String> values) {
            addCriterion("disease_alias not in", values, "diseaseAlias");
            return (Criteria) this;
        }

        public Criteria andDiseaseAliasBetween(String value1, String value2) {
            addCriterion("disease_alias between", value1, value2, "diseaseAlias");
            return (Criteria) this;
        }

        public Criteria andDiseaseAliasNotBetween(String value1, String value2) {
            addCriterion("disease_alias not between", value1, value2, "diseaseAlias");
            return (Criteria) this;
        }

        public Criteria andDiseaseIntroduceIsNull() {
            addCriterion("disease_introduce is null");
            return (Criteria) this;
        }

        public Criteria andDiseaseIntroduceIsNotNull() {
            addCriterion("disease_introduce is not null");
            return (Criteria) this;
        }

        public Criteria andDiseaseIntroduceEqualTo(String value) {
            addCriterion("disease_introduce =", value, "diseaseIntroduce");
            return (Criteria) this;
        }

        public Criteria andDiseaseIntroduceNotEqualTo(String value) {
            addCriterion("disease_introduce <>", value, "diseaseIntroduce");
            return (Criteria) this;
        }

        public Criteria andDiseaseIntroduceGreaterThan(String value) {
            addCriterion("disease_introduce >", value, "diseaseIntroduce");
            return (Criteria) this;
        }

        public Criteria andDiseaseIntroduceGreaterThanOrEqualTo(String value) {
            addCriterion("disease_introduce >=", value, "diseaseIntroduce");
            return (Criteria) this;
        }

        public Criteria andDiseaseIntroduceLessThan(String value) {
            addCriterion("disease_introduce <", value, "diseaseIntroduce");
            return (Criteria) this;
        }

        public Criteria andDiseaseIntroduceLessThanOrEqualTo(String value) {
            addCriterion("disease_introduce <=", value, "diseaseIntroduce");
            return (Criteria) this;
        }

        public Criteria andDiseaseIntroduceLike(String value) {
            addCriterion("disease_introduce like", value, "diseaseIntroduce");
            return (Criteria) this;
        }

        public Criteria andDiseaseIntroduceNotLike(String value) {
            addCriterion("disease_introduce not like", value, "diseaseIntroduce");
            return (Criteria) this;
        }

        public Criteria andDiseaseIntroduceIn(List<String> values) {
            addCriterion("disease_introduce in", values, "diseaseIntroduce");
            return (Criteria) this;
        }

        public Criteria andDiseaseIntroduceNotIn(List<String> values) {
            addCriterion("disease_introduce not in", values, "diseaseIntroduce");
            return (Criteria) this;
        }

        public Criteria andDiseaseIntroduceBetween(String value1, String value2) {
            addCriterion("disease_introduce between", value1, value2, "diseaseIntroduce");
            return (Criteria) this;
        }

        public Criteria andDiseaseIntroduceNotBetween(String value1, String value2) {
            addCriterion("disease_introduce not between", value1, value2, "diseaseIntroduce");
            return (Criteria) this;
        }

        public Criteria andDiseaseContagiousIsNull() {
            addCriterion("disease_contagious is null");
            return (Criteria) this;
        }

        public Criteria andDiseaseContagiousIsNotNull() {
            addCriterion("disease_contagious is not null");
            return (Criteria) this;
        }

        public Criteria andDiseaseContagiousEqualTo(String value) {
            addCriterion("disease_contagious =", value, "diseaseContagious");
            return (Criteria) this;
        }

        public Criteria andDiseaseContagiousNotEqualTo(String value) {
            addCriterion("disease_contagious <>", value, "diseaseContagious");
            return (Criteria) this;
        }

        public Criteria andDiseaseContagiousGreaterThan(String value) {
            addCriterion("disease_contagious >", value, "diseaseContagious");
            return (Criteria) this;
        }

        public Criteria andDiseaseContagiousGreaterThanOrEqualTo(String value) {
            addCriterion("disease_contagious >=", value, "diseaseContagious");
            return (Criteria) this;
        }

        public Criteria andDiseaseContagiousLessThan(String value) {
            addCriterion("disease_contagious <", value, "diseaseContagious");
            return (Criteria) this;
        }

        public Criteria andDiseaseContagiousLessThanOrEqualTo(String value) {
            addCriterion("disease_contagious <=", value, "diseaseContagious");
            return (Criteria) this;
        }

        public Criteria andDiseaseContagiousLike(String value) {
            addCriterion("disease_contagious like", value, "diseaseContagious");
            return (Criteria) this;
        }

        public Criteria andDiseaseContagiousNotLike(String value) {
            addCriterion("disease_contagious not like", value, "diseaseContagious");
            return (Criteria) this;
        }

        public Criteria andDiseaseContagiousIn(List<String> values) {
            addCriterion("disease_contagious in", values, "diseaseContagious");
            return (Criteria) this;
        }

        public Criteria andDiseaseContagiousNotIn(List<String> values) {
            addCriterion("disease_contagious not in", values, "diseaseContagious");
            return (Criteria) this;
        }

        public Criteria andDiseaseContagiousBetween(String value1, String value2) {
            addCriterion("disease_contagious between", value1, value2, "diseaseContagious");
            return (Criteria) this;
        }

        public Criteria andDiseaseContagiousNotBetween(String value1, String value2) {
            addCriterion("disease_contagious not between", value1, value2, "diseaseContagious");
            return (Criteria) this;
        }

        public Criteria andCureRateIsNull() {
            addCriterion("cure_rate is null");
            return (Criteria) this;
        }

        public Criteria andCureRateIsNotNull() {
            addCriterion("cure_rate is not null");
            return (Criteria) this;
        }

        public Criteria andCureRateEqualTo(String value) {
            addCriterion("cure_rate =", value, "cureRate");
            return (Criteria) this;
        }

        public Criteria andCureRateNotEqualTo(String value) {
            addCriterion("cure_rate <>", value, "cureRate");
            return (Criteria) this;
        }

        public Criteria andCureRateGreaterThan(String value) {
            addCriterion("cure_rate >", value, "cureRate");
            return (Criteria) this;
        }

        public Criteria andCureRateGreaterThanOrEqualTo(String value) {
            addCriterion("cure_rate >=", value, "cureRate");
            return (Criteria) this;
        }

        public Criteria andCureRateLessThan(String value) {
            addCriterion("cure_rate <", value, "cureRate");
            return (Criteria) this;
        }

        public Criteria andCureRateLessThanOrEqualTo(String value) {
            addCriterion("cure_rate <=", value, "cureRate");
            return (Criteria) this;
        }

        public Criteria andCureRateLike(String value) {
            addCriterion("cure_rate like", value, "cureRate");
            return (Criteria) this;
        }

        public Criteria andCureRateNotLike(String value) {
            addCriterion("cure_rate not like", value, "cureRate");
            return (Criteria) this;
        }

        public Criteria andCureRateIn(List<String> values) {
            addCriterion("cure_rate in", values, "cureRate");
            return (Criteria) this;
        }

        public Criteria andCureRateNotIn(List<String> values) {
            addCriterion("cure_rate not in", values, "cureRate");
            return (Criteria) this;
        }

        public Criteria andCureRateBetween(String value1, String value2) {
            addCriterion("cure_rate between", value1, value2, "cureRate");
            return (Criteria) this;
        }

        public Criteria andCureRateNotBetween(String value1, String value2) {
            addCriterion("cure_rate not between", value1, value2, "cureRate");
            return (Criteria) this;
        }

        public Criteria andCureDepartIsNull() {
            addCriterion("cure_depart is null");
            return (Criteria) this;
        }

        public Criteria andCureDepartIsNotNull() {
            addCriterion("cure_depart is not null");
            return (Criteria) this;
        }

        public Criteria andCureDepartEqualTo(String value) {
            addCriterion("cure_depart =", value, "cureDepart");
            return (Criteria) this;
        }

        public Criteria andCureDepartNotEqualTo(String value) {
            addCriterion("cure_depart <>", value, "cureDepart");
            return (Criteria) this;
        }

        public Criteria andCureDepartGreaterThan(String value) {
            addCriterion("cure_depart >", value, "cureDepart");
            return (Criteria) this;
        }

        public Criteria andCureDepartGreaterThanOrEqualTo(String value) {
            addCriterion("cure_depart >=", value, "cureDepart");
            return (Criteria) this;
        }

        public Criteria andCureDepartLessThan(String value) {
            addCriterion("cure_depart <", value, "cureDepart");
            return (Criteria) this;
        }

        public Criteria andCureDepartLessThanOrEqualTo(String value) {
            addCriterion("cure_depart <=", value, "cureDepart");
            return (Criteria) this;
        }

        public Criteria andCureDepartLike(String value) {
            addCriterion("cure_depart like", value, "cureDepart");
            return (Criteria) this;
        }

        public Criteria andCureDepartNotLike(String value) {
            addCriterion("cure_depart not like", value, "cureDepart");
            return (Criteria) this;
        }

        public Criteria andCureDepartIn(List<String> values) {
            addCriterion("cure_depart in", values, "cureDepart");
            return (Criteria) this;
        }

        public Criteria andCureDepartNotIn(List<String> values) {
            addCriterion("cure_depart not in", values, "cureDepart");
            return (Criteria) this;
        }

        public Criteria andCureDepartBetween(String value1, String value2) {
            addCriterion("cure_depart between", value1, value2, "cureDepart");
            return (Criteria) this;
        }

        public Criteria andCureDepartNotBetween(String value1, String value2) {
            addCriterion("cure_depart not between", value1, value2, "cureDepart");
            return (Criteria) this;
        }

        public Criteria andCureCostIsNull() {
            addCriterion("cure_cost is null");
            return (Criteria) this;
        }

        public Criteria andCureCostIsNotNull() {
            addCriterion("cure_cost is not null");
            return (Criteria) this;
        }

        public Criteria andCureCostEqualTo(String value) {
            addCriterion("cure_cost =", value, "cureCost");
            return (Criteria) this;
        }

        public Criteria andCureCostNotEqualTo(String value) {
            addCriterion("cure_cost <>", value, "cureCost");
            return (Criteria) this;
        }

        public Criteria andCureCostGreaterThan(String value) {
            addCriterion("cure_cost >", value, "cureCost");
            return (Criteria) this;
        }

        public Criteria andCureCostGreaterThanOrEqualTo(String value) {
            addCriterion("cure_cost >=", value, "cureCost");
            return (Criteria) this;
        }

        public Criteria andCureCostLessThan(String value) {
            addCriterion("cure_cost <", value, "cureCost");
            return (Criteria) this;
        }

        public Criteria andCureCostLessThanOrEqualTo(String value) {
            addCriterion("cure_cost <=", value, "cureCost");
            return (Criteria) this;
        }

        public Criteria andCureCostLike(String value) {
            addCriterion("cure_cost like", value, "cureCost");
            return (Criteria) this;
        }

        public Criteria andCureCostNotLike(String value) {
            addCriterion("cure_cost not like", value, "cureCost");
            return (Criteria) this;
        }

        public Criteria andCureCostIn(List<String> values) {
            addCriterion("cure_cost in", values, "cureCost");
            return (Criteria) this;
        }

        public Criteria andCureCostNotIn(List<String> values) {
            addCriterion("cure_cost not in", values, "cureCost");
            return (Criteria) this;
        }

        public Criteria andCureCostBetween(String value1, String value2) {
            addCriterion("cure_cost between", value1, value2, "cureCost");
            return (Criteria) this;
        }

        public Criteria andCureCostNotBetween(String value1, String value2) {
            addCriterion("cure_cost not between", value1, value2, "cureCost");
            return (Criteria) this;
        }

        public Criteria andCureWayIsNull() {
            addCriterion("cure_way is null");
            return (Criteria) this;
        }

        public Criteria andCureWayIsNotNull() {
            addCriterion("cure_way is not null");
            return (Criteria) this;
        }

        public Criteria andCureWayEqualTo(String value) {
            addCriterion("cure_way =", value, "cureWay");
            return (Criteria) this;
        }

        public Criteria andCureWayNotEqualTo(String value) {
            addCriterion("cure_way <>", value, "cureWay");
            return (Criteria) this;
        }

        public Criteria andCureWayGreaterThan(String value) {
            addCriterion("cure_way >", value, "cureWay");
            return (Criteria) this;
        }

        public Criteria andCureWayGreaterThanOrEqualTo(String value) {
            addCriterion("cure_way >=", value, "cureWay");
            return (Criteria) this;
        }

        public Criteria andCureWayLessThan(String value) {
            addCriterion("cure_way <", value, "cureWay");
            return (Criteria) this;
        }

        public Criteria andCureWayLessThanOrEqualTo(String value) {
            addCriterion("cure_way <=", value, "cureWay");
            return (Criteria) this;
        }

        public Criteria andCureWayLike(String value) {
            addCriterion("cure_way like", value, "cureWay");
            return (Criteria) this;
        }

        public Criteria andCureWayNotLike(String value) {
            addCriterion("cure_way not like", value, "cureWay");
            return (Criteria) this;
        }

        public Criteria andCureWayIn(List<String> values) {
            addCriterion("cure_way in", values, "cureWay");
            return (Criteria) this;
        }

        public Criteria andCureWayNotIn(List<String> values) {
            addCriterion("cure_way not in", values, "cureWay");
            return (Criteria) this;
        }

        public Criteria andCureWayBetween(String value1, String value2) {
            addCriterion("cure_way between", value1, value2, "cureWay");
            return (Criteria) this;
        }

        public Criteria andCureWayNotBetween(String value1, String value2) {
            addCriterion("cure_way not between", value1, value2, "cureWay");
            return (Criteria) this;
        }

        public Criteria andCureRecommendDrugIsNull() {
            addCriterion("cure_recommend_drug is null");
            return (Criteria) this;
        }

        public Criteria andCureRecommendDrugIsNotNull() {
            addCriterion("cure_recommend_drug is not null");
            return (Criteria) this;
        }

        public Criteria andCureRecommendDrugEqualTo(String value) {
            addCriterion("cure_recommend_drug =", value, "cureRecommendDrug");
            return (Criteria) this;
        }

        public Criteria andCureRecommendDrugNotEqualTo(String value) {
            addCriterion("cure_recommend_drug <>", value, "cureRecommendDrug");
            return (Criteria) this;
        }

        public Criteria andCureRecommendDrugGreaterThan(String value) {
            addCriterion("cure_recommend_drug >", value, "cureRecommendDrug");
            return (Criteria) this;
        }

        public Criteria andCureRecommendDrugGreaterThanOrEqualTo(String value) {
            addCriterion("cure_recommend_drug >=", value, "cureRecommendDrug");
            return (Criteria) this;
        }

        public Criteria andCureRecommendDrugLessThan(String value) {
            addCriterion("cure_recommend_drug <", value, "cureRecommendDrug");
            return (Criteria) this;
        }

        public Criteria andCureRecommendDrugLessThanOrEqualTo(String value) {
            addCriterion("cure_recommend_drug <=", value, "cureRecommendDrug");
            return (Criteria) this;
        }

        public Criteria andCureRecommendDrugLike(String value) {
            addCriterion("cure_recommend_drug like", value, "cureRecommendDrug");
            return (Criteria) this;
        }

        public Criteria andCureRecommendDrugNotLike(String value) {
            addCriterion("cure_recommend_drug not like", value, "cureRecommendDrug");
            return (Criteria) this;
        }

        public Criteria andCureRecommendDrugIn(List<String> values) {
            addCriterion("cure_recommend_drug in", values, "cureRecommendDrug");
            return (Criteria) this;
        }

        public Criteria andCureRecommendDrugNotIn(List<String> values) {
            addCriterion("cure_recommend_drug not in", values, "cureRecommendDrug");
            return (Criteria) this;
        }

        public Criteria andCureRecommendDrugBetween(String value1, String value2) {
            addCriterion("cure_recommend_drug between", value1, value2, "cureRecommendDrug");
            return (Criteria) this;
        }

        public Criteria andCureRecommendDrugNotBetween(String value1, String value2) {
            addCriterion("cure_recommend_drug not between", value1, value2, "cureRecommendDrug");
            return (Criteria) this;
        }

        public Criteria andDiseasePartIsNull() {
            addCriterion("disease_part is null");
            return (Criteria) this;
        }

        public Criteria andDiseasePartIsNotNull() {
            addCriterion("disease_part is not null");
            return (Criteria) this;
        }

        public Criteria andDiseasePartEqualTo(String value) {
            addCriterion("disease_part =", value, "diseasePart");
            return (Criteria) this;
        }

        public Criteria andDiseasePartNotEqualTo(String value) {
            addCriterion("disease_part <>", value, "diseasePart");
            return (Criteria) this;
        }

        public Criteria andDiseasePartGreaterThan(String value) {
            addCriterion("disease_part >", value, "diseasePart");
            return (Criteria) this;
        }

        public Criteria andDiseasePartGreaterThanOrEqualTo(String value) {
            addCriterion("disease_part >=", value, "diseasePart");
            return (Criteria) this;
        }

        public Criteria andDiseasePartLessThan(String value) {
            addCriterion("disease_part <", value, "diseasePart");
            return (Criteria) this;
        }

        public Criteria andDiseasePartLessThanOrEqualTo(String value) {
            addCriterion("disease_part <=", value, "diseasePart");
            return (Criteria) this;
        }

        public Criteria andDiseasePartLike(String value) {
            addCriterion("disease_part like", value, "diseasePart");
            return (Criteria) this;
        }

        public Criteria andDiseasePartNotLike(String value) {
            addCriterion("disease_part not like", value, "diseasePart");
            return (Criteria) this;
        }

        public Criteria andDiseasePartIn(List<String> values) {
            addCriterion("disease_part in", values, "diseasePart");
            return (Criteria) this;
        }

        public Criteria andDiseasePartNotIn(List<String> values) {
            addCriterion("disease_part not in", values, "diseasePart");
            return (Criteria) this;
        }

        public Criteria andDiseasePartBetween(String value1, String value2) {
            addCriterion("disease_part between", value1, value2, "diseasePart");
            return (Criteria) this;
        }

        public Criteria andDiseasePartNotBetween(String value1, String value2) {
            addCriterion("disease_part not between", value1, value2, "diseasePart");
            return (Criteria) this;
        }

        public Criteria andMedicalInsuranceIsNull() {
            addCriterion("medical_insurance is null");
            return (Criteria) this;
        }

        public Criteria andMedicalInsuranceIsNotNull() {
            addCriterion("medical_insurance is not null");
            return (Criteria) this;
        }

        public Criteria andMedicalInsuranceEqualTo(String value) {
            addCriterion("medical_insurance =", value, "medicalInsurance");
            return (Criteria) this;
        }

        public Criteria andMedicalInsuranceNotEqualTo(String value) {
            addCriterion("medical_insurance <>", value, "medicalInsurance");
            return (Criteria) this;
        }

        public Criteria andMedicalInsuranceGreaterThan(String value) {
            addCriterion("medical_insurance >", value, "medicalInsurance");
            return (Criteria) this;
        }

        public Criteria andMedicalInsuranceGreaterThanOrEqualTo(String value) {
            addCriterion("medical_insurance >=", value, "medicalInsurance");
            return (Criteria) this;
        }

        public Criteria andMedicalInsuranceLessThan(String value) {
            addCriterion("medical_insurance <", value, "medicalInsurance");
            return (Criteria) this;
        }

        public Criteria andMedicalInsuranceLessThanOrEqualTo(String value) {
            addCriterion("medical_insurance <=", value, "medicalInsurance");
            return (Criteria) this;
        }

        public Criteria andMedicalInsuranceLike(String value) {
            addCriterion("medical_insurance like", value, "medicalInsurance");
            return (Criteria) this;
        }

        public Criteria andMedicalInsuranceNotLike(String value) {
            addCriterion("medical_insurance not like", value, "medicalInsurance");
            return (Criteria) this;
        }

        public Criteria andMedicalInsuranceIn(List<String> values) {
            addCriterion("medical_insurance in", values, "medicalInsurance");
            return (Criteria) this;
        }

        public Criteria andMedicalInsuranceNotIn(List<String> values) {
            addCriterion("medical_insurance not in", values, "medicalInsurance");
            return (Criteria) this;
        }

        public Criteria andMedicalInsuranceBetween(String value1, String value2) {
            addCriterion("medical_insurance between", value1, value2, "medicalInsurance");
            return (Criteria) this;
        }

        public Criteria andMedicalInsuranceNotBetween(String value1, String value2) {
            addCriterion("medical_insurance not between", value1, value2, "medicalInsurance");
            return (Criteria) this;
        }

        public Criteria andCureTimeIsNull() {
            addCriterion("cure_time is null");
            return (Criteria) this;
        }

        public Criteria andCureTimeIsNotNull() {
            addCriterion("cure_time is not null");
            return (Criteria) this;
        }

        public Criteria andCureTimeEqualTo(String value) {
            addCriterion("cure_time =", value, "cureTime");
            return (Criteria) this;
        }

        public Criteria andCureTimeNotEqualTo(String value) {
            addCriterion("cure_time <>", value, "cureTime");
            return (Criteria) this;
        }

        public Criteria andCureTimeGreaterThan(String value) {
            addCriterion("cure_time >", value, "cureTime");
            return (Criteria) this;
        }

        public Criteria andCureTimeGreaterThanOrEqualTo(String value) {
            addCriterion("cure_time >=", value, "cureTime");
            return (Criteria) this;
        }

        public Criteria andCureTimeLessThan(String value) {
            addCriterion("cure_time <", value, "cureTime");
            return (Criteria) this;
        }

        public Criteria andCureTimeLessThanOrEqualTo(String value) {
            addCriterion("cure_time <=", value, "cureTime");
            return (Criteria) this;
        }

        public Criteria andCureTimeLike(String value) {
            addCriterion("cure_time like", value, "cureTime");
            return (Criteria) this;
        }

        public Criteria andCureTimeNotLike(String value) {
            addCriterion("cure_time not like", value, "cureTime");
            return (Criteria) this;
        }

        public Criteria andCureTimeIn(List<String> values) {
            addCriterion("cure_time in", values, "cureTime");
            return (Criteria) this;
        }

        public Criteria andCureTimeNotIn(List<String> values) {
            addCriterion("cure_time not in", values, "cureTime");
            return (Criteria) this;
        }

        public Criteria andCureTimeBetween(String value1, String value2) {
            addCriterion("cure_time between", value1, value2, "cureTime");
            return (Criteria) this;
        }

        public Criteria andCureTimeNotBetween(String value1, String value2) {
            addCriterion("cure_time not between", value1, value2, "cureTime");
            return (Criteria) this;
        }

        public Criteria andDiseaseComplicationIsNull() {
            addCriterion("disease_complication is null");
            return (Criteria) this;
        }

        public Criteria andDiseaseComplicationIsNotNull() {
            addCriterion("disease_complication is not null");
            return (Criteria) this;
        }

        public Criteria andDiseaseComplicationEqualTo(String value) {
            addCriterion("disease_complication =", value, "diseaseComplication");
            return (Criteria) this;
        }

        public Criteria andDiseaseComplicationNotEqualTo(String value) {
            addCriterion("disease_complication <>", value, "diseaseComplication");
            return (Criteria) this;
        }

        public Criteria andDiseaseComplicationGreaterThan(String value) {
            addCriterion("disease_complication >", value, "diseaseComplication");
            return (Criteria) this;
        }

        public Criteria andDiseaseComplicationGreaterThanOrEqualTo(String value) {
            addCriterion("disease_complication >=", value, "diseaseComplication");
            return (Criteria) this;
        }

        public Criteria andDiseaseComplicationLessThan(String value) {
            addCriterion("disease_complication <", value, "diseaseComplication");
            return (Criteria) this;
        }

        public Criteria andDiseaseComplicationLessThanOrEqualTo(String value) {
            addCriterion("disease_complication <=", value, "diseaseComplication");
            return (Criteria) this;
        }

        public Criteria andDiseaseComplicationLike(String value) {
            addCriterion("disease_complication like", value, "diseaseComplication");
            return (Criteria) this;
        }

        public Criteria andDiseaseComplicationNotLike(String value) {
            addCriterion("disease_complication not like", value, "diseaseComplication");
            return (Criteria) this;
        }

        public Criteria andDiseaseComplicationIn(List<String> values) {
            addCriterion("disease_complication in", values, "diseaseComplication");
            return (Criteria) this;
        }

        public Criteria andDiseaseComplicationNotIn(List<String> values) {
            addCriterion("disease_complication not in", values, "diseaseComplication");
            return (Criteria) this;
        }

        public Criteria andDiseaseComplicationBetween(String value1, String value2) {
            addCriterion("disease_complication between", value1, value2, "diseaseComplication");
            return (Criteria) this;
        }

        public Criteria andDiseaseComplicationNotBetween(String value1, String value2) {
            addCriterion("disease_complication not between", value1, value2, "diseaseComplication");
            return (Criteria) this;
        }

        public Criteria andDiseasePopulationIsNull() {
            addCriterion("disease_population is null");
            return (Criteria) this;
        }

        public Criteria andDiseasePopulationIsNotNull() {
            addCriterion("disease_population is not null");
            return (Criteria) this;
        }

        public Criteria andDiseasePopulationEqualTo(String value) {
            addCriterion("disease_population =", value, "diseasePopulation");
            return (Criteria) this;
        }

        public Criteria andDiseasePopulationNotEqualTo(String value) {
            addCriterion("disease_population <>", value, "diseasePopulation");
            return (Criteria) this;
        }

        public Criteria andDiseasePopulationGreaterThan(String value) {
            addCriterion("disease_population >", value, "diseasePopulation");
            return (Criteria) this;
        }

        public Criteria andDiseasePopulationGreaterThanOrEqualTo(String value) {
            addCriterion("disease_population >=", value, "diseasePopulation");
            return (Criteria) this;
        }

        public Criteria andDiseasePopulationLessThan(String value) {
            addCriterion("disease_population <", value, "diseasePopulation");
            return (Criteria) this;
        }

        public Criteria andDiseasePopulationLessThanOrEqualTo(String value) {
            addCriterion("disease_population <=", value, "diseasePopulation");
            return (Criteria) this;
        }

        public Criteria andDiseasePopulationLike(String value) {
            addCriterion("disease_population like", value, "diseasePopulation");
            return (Criteria) this;
        }

        public Criteria andDiseasePopulationNotLike(String value) {
            addCriterion("disease_population not like", value, "diseasePopulation");
            return (Criteria) this;
        }

        public Criteria andDiseasePopulationIn(List<String> values) {
            addCriterion("disease_population in", values, "diseasePopulation");
            return (Criteria) this;
        }

        public Criteria andDiseasePopulationNotIn(List<String> values) {
            addCriterion("disease_population not in", values, "diseasePopulation");
            return (Criteria) this;
        }

        public Criteria andDiseasePopulationBetween(String value1, String value2) {
            addCriterion("disease_population between", value1, value2, "diseasePopulation");
            return (Criteria) this;
        }

        public Criteria andDiseasePopulationNotBetween(String value1, String value2) {
            addCriterion("disease_population not between", value1, value2, "diseasePopulation");
            return (Criteria) this;
        }

        public Criteria andDiseaseTypicalSymptomsIsNull() {
            addCriterion("disease_typical_symptoms is null");
            return (Criteria) this;
        }

        public Criteria andDiseaseTypicalSymptomsIsNotNull() {
            addCriterion("disease_typical_symptoms is not null");
            return (Criteria) this;
        }

        public Criteria andDiseaseTypicalSymptomsEqualTo(String value) {
            addCriterion("disease_typical_symptoms =", value, "diseaseTypicalSymptoms");
            return (Criteria) this;
        }

        public Criteria andDiseaseTypicalSymptomsNotEqualTo(String value) {
            addCriterion("disease_typical_symptoms <>", value, "diseaseTypicalSymptoms");
            return (Criteria) this;
        }

        public Criteria andDiseaseTypicalSymptomsGreaterThan(String value) {
            addCriterion("disease_typical_symptoms >", value, "diseaseTypicalSymptoms");
            return (Criteria) this;
        }

        public Criteria andDiseaseTypicalSymptomsGreaterThanOrEqualTo(String value) {
            addCriterion("disease_typical_symptoms >=", value, "diseaseTypicalSymptoms");
            return (Criteria) this;
        }

        public Criteria andDiseaseTypicalSymptomsLessThan(String value) {
            addCriterion("disease_typical_symptoms <", value, "diseaseTypicalSymptoms");
            return (Criteria) this;
        }

        public Criteria andDiseaseTypicalSymptomsLessThanOrEqualTo(String value) {
            addCriterion("disease_typical_symptoms <=", value, "diseaseTypicalSymptoms");
            return (Criteria) this;
        }

        public Criteria andDiseaseTypicalSymptomsLike(String value) {
            addCriterion("disease_typical_symptoms like", value, "diseaseTypicalSymptoms");
            return (Criteria) this;
        }

        public Criteria andDiseaseTypicalSymptomsNotLike(String value) {
            addCriterion("disease_typical_symptoms not like", value, "diseaseTypicalSymptoms");
            return (Criteria) this;
        }

        public Criteria andDiseaseTypicalSymptomsIn(List<String> values) {
            addCriterion("disease_typical_symptoms in", values, "diseaseTypicalSymptoms");
            return (Criteria) this;
        }

        public Criteria andDiseaseTypicalSymptomsNotIn(List<String> values) {
            addCriterion("disease_typical_symptoms not in", values, "diseaseTypicalSymptoms");
            return (Criteria) this;
        }

        public Criteria andDiseaseTypicalSymptomsBetween(String value1, String value2) {
            addCriterion("disease_typical_symptoms between", value1, value2, "diseaseTypicalSymptoms");
            return (Criteria) this;
        }

        public Criteria andDiseaseTypicalSymptomsNotBetween(String value1, String value2) {
            addCriterion("disease_typical_symptoms not between", value1, value2, "diseaseTypicalSymptoms");
            return (Criteria) this;
        }

        public Criteria andDiseasePreventionIsNull() {
            addCriterion("disease_prevention is null");
            return (Criteria) this;
        }

        public Criteria andDiseasePreventionIsNotNull() {
            addCriterion("disease_prevention is not null");
            return (Criteria) this;
        }

        public Criteria andDiseasePreventionEqualTo(String value) {
            addCriterion("disease_prevention =", value, "diseasePrevention");
            return (Criteria) this;
        }

        public Criteria andDiseasePreventionNotEqualTo(String value) {
            addCriterion("disease_prevention <>", value, "diseasePrevention");
            return (Criteria) this;
        }

        public Criteria andDiseasePreventionGreaterThan(String value) {
            addCriterion("disease_prevention >", value, "diseasePrevention");
            return (Criteria) this;
        }

        public Criteria andDiseasePreventionGreaterThanOrEqualTo(String value) {
            addCriterion("disease_prevention >=", value, "diseasePrevention");
            return (Criteria) this;
        }

        public Criteria andDiseasePreventionLessThan(String value) {
            addCriterion("disease_prevention <", value, "diseasePrevention");
            return (Criteria) this;
        }

        public Criteria andDiseasePreventionLessThanOrEqualTo(String value) {
            addCriterion("disease_prevention <=", value, "diseasePrevention");
            return (Criteria) this;
        }

        public Criteria andDiseasePreventionLike(String value) {
            addCriterion("disease_prevention like", value, "diseasePrevention");
            return (Criteria) this;
        }

        public Criteria andDiseasePreventionNotLike(String value) {
            addCriterion("disease_prevention not like", value, "diseasePrevention");
            return (Criteria) this;
        }

        public Criteria andDiseasePreventionIn(List<String> values) {
            addCriterion("disease_prevention in", values, "diseasePrevention");
            return (Criteria) this;
        }

        public Criteria andDiseasePreventionNotIn(List<String> values) {
            addCriterion("disease_prevention not in", values, "diseasePrevention");
            return (Criteria) this;
        }

        public Criteria andDiseasePreventionBetween(String value1, String value2) {
            addCriterion("disease_prevention between", value1, value2, "diseasePrevention");
            return (Criteria) this;
        }

        public Criteria andDiseasePreventionNotBetween(String value1, String value2) {
            addCriterion("disease_prevention not between", value1, value2, "diseasePrevention");
            return (Criteria) this;
        }

        public Criteria andDiseaseCaseIsNull() {
            addCriterion("disease_case is null");
            return (Criteria) this;
        }

        public Criteria andDiseaseCaseIsNotNull() {
            addCriterion("disease_case is not null");
            return (Criteria) this;
        }

        public Criteria andDiseaseCaseEqualTo(String value) {
            addCriterion("disease_case =", value, "diseaseCase");
            return (Criteria) this;
        }

        public Criteria andDiseaseCaseNotEqualTo(String value) {
            addCriterion("disease_case <>", value, "diseaseCase");
            return (Criteria) this;
        }

        public Criteria andDiseaseCaseGreaterThan(String value) {
            addCriterion("disease_case >", value, "diseaseCase");
            return (Criteria) this;
        }

        public Criteria andDiseaseCaseGreaterThanOrEqualTo(String value) {
            addCriterion("disease_case >=", value, "diseaseCase");
            return (Criteria) this;
        }

        public Criteria andDiseaseCaseLessThan(String value) {
            addCriterion("disease_case <", value, "diseaseCase");
            return (Criteria) this;
        }

        public Criteria andDiseaseCaseLessThanOrEqualTo(String value) {
            addCriterion("disease_case <=", value, "diseaseCase");
            return (Criteria) this;
        }

        public Criteria andDiseaseCaseLike(String value) {
            addCriterion("disease_case like", value, "diseaseCase");
            return (Criteria) this;
        }

        public Criteria andDiseaseCaseNotLike(String value) {
            addCriterion("disease_case not like", value, "diseaseCase");
            return (Criteria) this;
        }

        public Criteria andDiseaseCaseIn(List<String> values) {
            addCriterion("disease_case in", values, "diseaseCase");
            return (Criteria) this;
        }

        public Criteria andDiseaseCaseNotIn(List<String> values) {
            addCriterion("disease_case not in", values, "diseaseCase");
            return (Criteria) this;
        }

        public Criteria andDiseaseCaseBetween(String value1, String value2) {
            addCriterion("disease_case between", value1, value2, "diseaseCase");
            return (Criteria) this;
        }

        public Criteria andDiseaseCaseNotBetween(String value1, String value2) {
            addCriterion("disease_case not between", value1, value2, "diseaseCase");
            return (Criteria) this;
        }

        public Criteria andDiseaseCheckIsNull() {
            addCriterion("disease_check is null");
            return (Criteria) this;
        }

        public Criteria andDiseaseCheckIsNotNull() {
            addCriterion("disease_check is not null");
            return (Criteria) this;
        }

        public Criteria andDiseaseCheckEqualTo(String value) {
            addCriterion("disease_check =", value, "diseaseCheck");
            return (Criteria) this;
        }

        public Criteria andDiseaseCheckNotEqualTo(String value) {
            addCriterion("disease_check <>", value, "diseaseCheck");
            return (Criteria) this;
        }

        public Criteria andDiseaseCheckGreaterThan(String value) {
            addCriterion("disease_check >", value, "diseaseCheck");
            return (Criteria) this;
        }

        public Criteria andDiseaseCheckGreaterThanOrEqualTo(String value) {
            addCriterion("disease_check >=", value, "diseaseCheck");
            return (Criteria) this;
        }

        public Criteria andDiseaseCheckLessThan(String value) {
            addCriterion("disease_check <", value, "diseaseCheck");
            return (Criteria) this;
        }

        public Criteria andDiseaseCheckLessThanOrEqualTo(String value) {
            addCriterion("disease_check <=", value, "diseaseCheck");
            return (Criteria) this;
        }

        public Criteria andDiseaseCheckLike(String value) {
            addCriterion("disease_check like", value, "diseaseCheck");
            return (Criteria) this;
        }

        public Criteria andDiseaseCheckNotLike(String value) {
            addCriterion("disease_check not like", value, "diseaseCheck");
            return (Criteria) this;
        }

        public Criteria andDiseaseCheckIn(List<String> values) {
            addCriterion("disease_check in", values, "diseaseCheck");
            return (Criteria) this;
        }

        public Criteria andDiseaseCheckNotIn(List<String> values) {
            addCriterion("disease_check not in", values, "diseaseCheck");
            return (Criteria) this;
        }

        public Criteria andDiseaseCheckBetween(String value1, String value2) {
            addCriterion("disease_check between", value1, value2, "diseaseCheck");
            return (Criteria) this;
        }

        public Criteria andDiseaseCheckNotBetween(String value1, String value2) {
            addCriterion("disease_check not between", value1, value2, "diseaseCheck");
            return (Criteria) this;
        }

        public Criteria andDiseaseUrlIsNull() {
            addCriterion("disease_url is null");
            return (Criteria) this;
        }

        public Criteria andDiseaseUrlIsNotNull() {
            addCriterion("disease_url is not null");
            return (Criteria) this;
        }

        public Criteria andDiseaseUrlEqualTo(String value) {
            addCriterion("disease_url =", value, "diseaseUrl");
            return (Criteria) this;
        }

        public Criteria andDiseaseUrlNotEqualTo(String value) {
            addCriterion("disease_url <>", value, "diseaseUrl");
            return (Criteria) this;
        }

        public Criteria andDiseaseUrlGreaterThan(String value) {
            addCriterion("disease_url >", value, "diseaseUrl");
            return (Criteria) this;
        }

        public Criteria andDiseaseUrlGreaterThanOrEqualTo(String value) {
            addCriterion("disease_url >=", value, "diseaseUrl");
            return (Criteria) this;
        }

        public Criteria andDiseaseUrlLessThan(String value) {
            addCriterion("disease_url <", value, "diseaseUrl");
            return (Criteria) this;
        }

        public Criteria andDiseaseUrlLessThanOrEqualTo(String value) {
            addCriterion("disease_url <=", value, "diseaseUrl");
            return (Criteria) this;
        }

        public Criteria andDiseaseUrlLike(String value) {
            addCriterion("disease_url like", value, "diseaseUrl");
            return (Criteria) this;
        }

        public Criteria andDiseaseUrlNotLike(String value) {
            addCriterion("disease_url not like", value, "diseaseUrl");
            return (Criteria) this;
        }

        public Criteria andDiseaseUrlIn(List<String> values) {
            addCriterion("disease_url in", values, "diseaseUrl");
            return (Criteria) this;
        }

        public Criteria andDiseaseUrlNotIn(List<String> values) {
            addCriterion("disease_url not in", values, "diseaseUrl");
            return (Criteria) this;
        }

        public Criteria andDiseaseUrlBetween(String value1, String value2) {
            addCriterion("disease_url between", value1, value2, "diseaseUrl");
            return (Criteria) this;
        }

        public Criteria andDiseaseUrlNotBetween(String value1, String value2) {
            addCriterion("disease_url not between", value1, value2, "diseaseUrl");
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