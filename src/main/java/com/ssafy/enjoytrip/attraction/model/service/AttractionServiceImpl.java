package com.ssafy.enjoytrip.attraction.model.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ssafy.enjoytrip.attraction.model.dao.AttractionDao;
import com.ssafy.enjoytrip.attraction.model.dto.AttractionInfoDto;
import com.ssafy.enjoytrip.attraction.model.dto.AttractionInfoWithDistanceDto;
import com.ssafy.enjoytrip.attraction.model.dto.GugunDto;
import com.ssafy.enjoytrip.attraction.model.dto.SidoDto;
import com.ssafy.enjoytrip.util.DtoConverter;
import com.ssafy.enjoytrip.util.Haversine;
import com.ssafy.enjoytrip.util.PageNavigation;
import com.ssafy.enjoytrip.util.SizeConstant;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class AttractionServiceImpl implements AttractionService {
	private final AttractionDao attractionDao;
	
	@Override
	public int totalAttractionListCount(AttractionInfoDto attractionInfoDto) {
		return attractionDao.totalAttractionListCount(attractionInfoDto);
	}
	
	@Override
	public List<AttractionInfoDto> search(AttractionInfoDto attractionInfoDto) {
		return attractionDao.search(attractionInfoDto);
	}
	
	@Override
	public List<AttractionInfoDto> searchWithPaging(AttractionInfoDto attractionInfoDto, PageNavigation pageNavigation) {
		int currentPage = pageNavigation.getCurrentPage();
		int sizePerPage = SizeConstant.LIST_SIZE;
		
		int start = (currentPage - 1) * sizePerPage;
		
		return attractionDao.searchWithPaging(attractionInfoDto, start, sizePerPage);
	}

	@Override
	public List<AttractionInfoDto> searchByTitle(String title, int sidoCode) {
		return attractionDao.searchByTitle(title, sidoCode);
	}
	
	@Override
	public List<AttractionInfoWithDistanceDto> sortByDistance(AttractionInfoDto attractionInfoDto, int distance) {
		DtoConverter<AttractionInfoDto, AttractionInfoWithDistanceDto> converter = new DtoConverter<>();
		return attractionDao.search(attractionInfoDto).parallelStream()
				.filter(attraction -> (Haversine.getDistanceInMeter(attractionInfoDto.getLatitude(), attractionInfoDto.getLongitude(), attraction.getLatitude(), attraction.getLongitude())) <= distance)
				.map(attactionInfoDto -> converter.convert(attactionInfoDto, new AttractionInfoWithDistanceDto()))
				.peek((attractionInfoWithDistanceDto) -> 
					attractionInfoWithDistanceDto.setDistance(
						Haversine.getDistanceInMeter(
							attractionInfoDto.getLatitude(), 
							attractionInfoDto.getLongitude(), 
							attractionInfoWithDistanceDto.getLatitude(), 
							attractionInfoWithDistanceDto.getLongitude()
						)
					)
				)
				.sorted((attraction, anotherAttraction) -> Double.compare(attraction.getDistance(), anotherAttraction.getDistance()))
				.collect(Collectors.toList());
	}
	
	@Override
	public List<SidoDto> searchAllSido() {
		return attractionDao.searchAllSido();
	}
	
	@Override
	public List<GugunDto> searchGugunBySido(int sidoCode) {
		return attractionDao.searchGugunBySido(sidoCode);
	}

}
