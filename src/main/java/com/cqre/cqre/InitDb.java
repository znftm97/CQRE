package com.cqre.cqre;

import com.cqre.cqre.dto.item.CreateItemDto;
import com.cqre.cqre.entity.GalleryFile;
import com.cqre.cqre.entity.User;
import com.cqre.cqre.entity.post.Board;
import com.cqre.cqre.entity.post.Post;
import com.cqre.cqre.entity.shop.ItemImage;
import com.cqre.cqre.entity.shop.item.Category;
import com.cqre.cqre.entity.shop.item.CommonItem;
import com.cqre.cqre.entity.shop.item.Item;
import com.cqre.cqre.entity.shop.item.ItemGender;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

@Component
@RequiredArgsConstructor
public class InitDb {

    private final InitService initService;

    @PostConstruct
    public void init(){
        initService.init();
        initService.categoryInit();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService{
        private final EntityManager em;
        private final PasswordEncoder passwordEncoder;

        public void init(){
            User user1 = User.builder()
                    .name("이지훈")
                    .studentId("20144339")
                    .loginId("znftm97")
                    .password(passwordEncoder.encode("wprkfrhdaud12!"))
                    .email("znftm97@gmail.com")
                    .build();

            User user2 = User.builder()
                    .name("홍길동")
                    .studentId("12341234")
                    .loginId("123")
                    .password(passwordEncoder.encode("123"))
                    .email("znftm93@gmail.com")
                    .build();

            user1.setEmailVerified("true");
            user2.setEmailVerified("true");
            em.persist(user1);
            em.persist(user2);

            /*post*/
            Post post1 = createPost("1111", "1111", 0, 0, user1, Board.FREE);
            Post post2 = createPost("2222", "2222", 0, 0, user1, Board.FREE);
            Post post3 = createPost("3333", "3333", 0, 0, user1, Board.FREE);
            Post post4 = createPost("4444", "4444", 0, 0, user1, Board.FREE);
            Post post5 = createPost("5555", "5555", 0, 0, user2, Board.FREE);
            Post post6 = createPost("6666", "6666", 0, 0, user2, Board.FREE);
            Post post7 = createPost("7777", "7777", 0, 0, user2, Board.FREE);
            Post post8 = createPost("1111", "1111", 0, 0, user2, Board.NOTICE);
            Post post9 = createPost("2222", "2222", 0, 0, user2, Board.NOTICE);

            em.persist(post1);
            em.persist(post2);
            em.persist(post3);
            em.persist(post4);
            em.persist(post5);
            em.persist(post6);
            em.persist(post7);
            em.persist(post8);
            em.persist(post9);

            /*galleryFile*/
            String filename1 = "111.jpg";
            String filename2 = "222.png";
            String filename3 = "333.jpg";
            String filename4 = "444.jpg";
            String filename5 = "555.jpg";
            String filename6 = "666.jpg";

            GalleryFile galleryFile1 = createGalleryFile("test", "test", filename1, "test", user2, 1000L, 111111L);
            GalleryFile galleryFile2 = createGalleryFile("test", "test", filename2, "test", user2, 1000L, 111111L);
            GalleryFile galleryFile3 = createGalleryFile("test", "test", filename3, "test", user2, 1000L, 111111L);
            GalleryFile galleryFile4 = createGalleryFile("test", "test", filename2, "test", user2, 2000L, 111111L);
            GalleryFile galleryFile5 = createGalleryFile("test", "test", filename3, "test", user2, 2000L, 111111L);
            GalleryFile galleryFile6 = createGalleryFile("test", "test", filename4, "test", user2, 2000L, 111111L);
            GalleryFile galleryFile7 = createGalleryFile("test", "test", filename3, "test", user2, 3000L, 111111L);
            GalleryFile galleryFile8 = createGalleryFile("test", "test", filename4, "test", user1, 4000L, 111111L);
            GalleryFile galleryFile9 = createGalleryFile("test", "test", filename5, "test", user1, 5000L, 111111L);
            GalleryFile galleryFile10 = createGalleryFile("test", "test", filename6, "test", user1, 6000L, 111111L);
            GalleryFile galleryFile11 = createGalleryFile("test", "test", filename6, "test", user1, 7000L, 111111L);

            em.persist(galleryFile1);
            em.persist(galleryFile2);
            em.persist(galleryFile3);
            em.persist(galleryFile4);
            em.persist(galleryFile5);
            em.persist(galleryFile6);
            em.persist(galleryFile7);
            em.persist(galleryFile8);
            em.persist(galleryFile9);
            em.persist(galleryFile10);
            em.persist(galleryFile11);
        }

        public void categoryInit(){
            Category category1 = createCategory("반팔 티셔츠", "1-1");
            Category category2 = createCategory("긴팔 티셔츠", "1-2");
            em.persist(category1);
            em.persist(category2);
            em.persist(createCategory("셔츠/블라우스", "1-3"));
            em.persist(createCategory("코튼 팬츠", "2-1"));
            em.persist(createCategory("데님 팬츠", "2-2"));
            em.persist(createCategory("레깅스", "2-3"));
            em.persist(createCategory("운동화", "3-1"));
            em.persist(createCategory("구두", "3-2"));
            em.persist(createCategory("샌들", "3-3"));


            CommonItem item1 = createItem("1-1", "설명설명ㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇ", "상품1", 10000, 100, "MEN", category1);
            CommonItem item2 = createItem("1-1", "설명설명~~~", "상품2", 10000, 100, "MEN", category1);
            CommonItem item3 = createItem("1-1", "설명설명~~~", "상품3", 10000, 100, "MEN", category1);
            CommonItem item4 = createItem("1-2", "설명설명~~~", "상품4", 10000, 100, "WOMEN", category2);
            CommonItem item5 = createItem("1-2", "설명설명~~~", "상품5", 10000, 100, "WOMEN", category2);
            CommonItem item6 = createItem("1-2", "설명설명~~~", "상품6", 10000, 100, "PUBLIC", category2);
            CommonItem item7 = createItem("1-2", "설명설명~~~", "상품7", 10000, 100, "PUBLIC", category2);
            em.persist(item1);
            em.persist(item2);
            em.persist(item3);
            em.persist(item4);
            em.persist(item5);
            em.persist(item6);
            em.persist(item7);

            String filename1 = "shop111.jpg";
            String filename2 = "shop222.jpg";
            String filename3 = "shop333.jpg";
            String filename4 = "shop444.png";
            String filename5 = "shop555.jpg";
            String filename6 = "shop666.jpg";

            em.persist(createItemImage(item1, 1000L, filename1, "test", "test"));
            em.persist(createItemImage(item1, 1000L, filename2, "test", "test"));
            em.persist(createItemImage(item2, 2000L, filename2, "test", "test"));
            em.persist(createItemImage(item2, 2000L, filename3, "test", "test"));
            em.persist(createItemImage(item3, 3000L, filename3, "test", "test"));
            em.persist(createItemImage(item4, 4000L, filename4, "test", "test"));
            em.persist(createItemImage(item5, 5000L, filename5, "test", "test"));
            em.persist(createItemImage(item6, 6000L, filename6, "test", "test"));
            em.persist(createItemImage(item7, 7000L, filename6, "test", "test"));
        }


        private Post createPost(String title, String content, int postVies, int recommendation, User user, Board board){
            return Post.builder()
                    .title(title)
                    .content(content)
                    .postViews(postVies)
                    .recommendation(recommendation)
                    .user(user)
                    .board(board)
                    .build();
        }

        private GalleryFile createGalleryFile(String title, String originFilename, String filename, String filepath, User user, Long bundleId, Long bundleOrder) {
            return GalleryFile.builder()
                    .title(title)
                    .filename(filename)
                    .filePath(filepath)
                    .originFilename(originFilename)
                    .user(user)
                    .bundleId(bundleId)
                    .bundleOrder(bundleOrder)
                    .build();
        }


        private Category createCategory(String name, String identificationCode) {
             return Category.builder()
                    .name(name)
                     .identificationCode(identificationCode)
                    .build();
        }

        private CommonItem createItem(String categorySelect, String itemExplanation, String name, int price, int stockCount, String gender, Category category){
            CreateItemDto dto = new CreateItemDto(name, itemExplanation, price, stockCount, categorySelect, gender);
            CommonItem commonItem = new CommonItem();
            return commonItem.createCommonItem(dto, category);
        }

        private ItemImage createItemImage(Item item, Long bundleId, String filename, String filePath, String originFilename) {
            return ItemImage.builder()
                    .item(item)
                    .bundleId(bundleId)
                    .bundleOrder(System.currentTimeMillis())
                    .filename(filename)
                    .filePath(filePath)
                    .originFilename(originFilename)
                    .build();
        }
    }
}
