select * from site
join campground on campground.campground_id = site.campground_id
where site.campground_id = 1
and site.site_id not in
      (select site_id from reservation
       WHERE '02/08/2020' BETWEEN from_date and to_date
       OR '02/14/2020' BETWEEN from_date and to_date);
       
       
       
       
      SELECT site.site_id, campground.name
       FROM campground 
        JOIN site ON campground.campground_id = site.campground_id "
			+ "LEFT JOIN reservation ON reservation.site_id = site.site_id "
			+ "WHERE (reservation.reservation_id IS NULL "
			+ "AND campground.campground_id = ? "
			+ "AND EXTRACT (MONTH FROM DATE ?) > CAST (campground.open_from_mm AS INTEGER) AND EXTRACT (MONTH FROM DATE ?) < CAST (campground.open_to_mm AS INTEGER) "
			+ "OR (reservation.reservation_id IS NOT NULL "
			+ "AND campground.campground_id = ? "
			+ "AND EXTRACT (MONTH FROM DATE ?) > CAST (campground.open_from_mm AS INTEGER) AND EXTRACT (MONTH FROM DATE ?) < CAST (campground.open_to_mm AS INTEGER) "
			+ " AND site.site_id NOT IN "