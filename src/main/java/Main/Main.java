package Main;

import Models.Board;
import Models.Card;
import Models.CustomList;
import Models.User;
import Services.BoardManagementSystem;
import Services.CardManager;
import Services.ListManager;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {

        List<Board> boards = new ArrayList<>();
        List<CustomList> lists = new ArrayList<>();
        List<Card> cards = new ArrayList<>();

        CardManager cardManager = new CardManager(cards);
        ListManager listManager = new ListManager(lists,cardManager);

        BoardManagementSystem service = new BoardManagementSystem(boards,listManager);

        //service.showAll();
        UUID id1 = service.createBoard("work@tech",null,null,null,null);

        //service.show(id1);

        service.updateBoardName(id1,"workat.tech");
        service.updateBoardPrivcy(id1, "PRIVATE");

        //service.show(id1);

        UUID id2 = service.createBoard("workat",null,null,null,null);

        //service.showAll();

        User user1 = new User(1,"U1","u1@gmail.com");
        User user2 = new User(2,"U2","u2@gmail.com");
        User user3 = new User(3,"U3","u3@gmail.com");

        service.addMember(id1, user1);
        service.addMember(id1, user2);
        service.addMember(id1, user3);
        service.removeMember(id1,user2);



        UUID listId1 = service.createList(id1, "Mock Interviews");
        //service.showAll();
        service.updateList(listId1, "Mock Interviews - Applied");
        UUID listId2 = service.createList(id1, "Mock Interviews - Scheduled");

        UUID cardId1 = service.createCard(listId1,"abcd@gmail.com");
        UUID cardId2 = service.createCard(listId1, "abcda@gmail.com");

        service.updateCardAssignee(cardId1,user1);

        service.showAll();




    }
}