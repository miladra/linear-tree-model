package com.codingchallenge.Mapper;

import com.codingchallenge.domain.TreeModel;
import com.codingchallenge.entity.TreeNode;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel="spring")
public interface TreeModelMapper {
    TreeModelMapper INSTANCE = Mappers.getMapper(TreeModelMapper.class);

    @Mapping( target = "subNodes", ignore = true )
    @Mapping( target = "subRootNodes", ignore = true )
    TreeModel toTreeModel(TreeNode entity);

    List<TreeModel> toListOfTreeModel(List<TreeNode> entity);

    TreeNode toTreeNode(TreeModel request);
}
