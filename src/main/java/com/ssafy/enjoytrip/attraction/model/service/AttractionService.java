package com.ssafy.enjoytrip.attraction.model.service;

import java.util.List;

import com.ssafy.enjoytrip.attraction.model.dto.AttractionInfoDto;
import com.ssafy.enjoytrip.attraction.model.dto.AttractionInfoWithDistanceDto;
import com.ssafy.enjoytrip.attraction.model.dto.GugunDto;
import com.ssafy.enjoytrip.attraction.model.dto.SidoDto;
import com.ssafy.enjoytrip.util.PageNavigation;

public interface AttractionService {
	int totalAttractionListCount(AttractionInfoDto attractionInfoDto);

	List<AttractionInfoDto> search(AttractionInfoDto attractionInfoDto);

	List<AttractionInfoDto> searchByTitle(String title, int sidoCode);

	List<AttractionInfoWithDistanceDto> sortByDistance(AttractionInfoDto attractionInfoDto, int distance);

	List<SidoDto> searchAllSido();

	List<GugunDto> searchGugunBySido(int sidoCode);

	List<AttractionInfoDto> searchWithPaging(AttractionInfoDto attractionInfoDto, PageNavigation pageNavigation);
}
