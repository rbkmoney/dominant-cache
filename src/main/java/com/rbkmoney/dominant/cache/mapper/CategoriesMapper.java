package com.rbkmoney.dominant.cache.mapper;

import com.github.benmanes.caffeine.cache.Cache;
import com.rbkmoney.damsel.domain.CategoryObject;
import com.rbkmoney.damsel.domain.CategoryType;
import com.rbkmoney.damsel.domain.DomainObject;
import com.rbkmoney.damsel.domain.Reference;
import com.rbkmoney.damsel.domain_config.Snapshot;
import com.rbkmoney.damsel.dominant.cache.Category;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.rbkmoney.dominant.cache.mapper.utils.DomainObjectMapExtractor.getDomainObjectMap;

public class CategoriesMapper {

    public static List<Category> mapCategories(Cache<String, Snapshot> cache) {
        Map<Reference, DomainObject> domainObjectMap = getDomainObjectMap(cache);
        List<Category> categoryList = new ArrayList<>();
        for (Map.Entry<Reference, DomainObject> entry : domainObjectMap.entrySet()) {
            if (entry.getKey().isSetCategory()) {
                Category category = new Category();
                CategoryObject categoryObject = entry.getValue().getCategory();
                category.setRef(String.valueOf(categoryObject.getRef().getId()));
                category.setName(categoryObject.getData().getName());
                category.setDescription(categoryObject.getData().getDescription());
                if (CategoryType.live.equals(categoryObject.getData().getType())) {
                    category.setType(com.rbkmoney.damsel.dominant.cache.CategoryType.live);
                } else if (CategoryType.test.equals(categoryObject.getData().getType())) {
                    category.setType(com.rbkmoney.damsel.dominant.cache.CategoryType.test);
                }
                categoryList.add(category);
            }
        }
        return categoryList;
    }
}
