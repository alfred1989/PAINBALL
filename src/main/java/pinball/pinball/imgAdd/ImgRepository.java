package pinball.pinball.imgAdd;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface ImgRepository extends PagingAndSortingRepository<Image, Long> {

    public Image findByName(String name);



}
