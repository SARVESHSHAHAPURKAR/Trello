package Services;

import Interfaces.ManagerInterface;
import Models.Card;
import Models.User;

import java.util.List;
import java.util.UUID;

public class CardManager implements ManagerInterface {

    List<Card> cards;

    public CardManager(List<Card> cards) {
        this.cards = cards;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    @Override
    public void show(UUID id) {

        for(Card card: cards){
            if(card.getId().equals(id)){

                if(card.getAssignedUser()==null){
                    card.setAssignedUser(new User(100, "null", "null"));
                }

                System.out.println("Card id is "+card.getId()+" name is "+card.getName()+" description is "
                + card.getDescription()+" assigned to "+card.getAssignedUser().getName());
            }
        }
    }

    public void updateName(UUID id, String name) {

        for(Card card: cards){
            if(card.getId().equals(id)){
                card.setName(name);
            }
        }
    }

    public void updateDesc(UUID id, String desc) {

        for(Card card: cards){
            if(card.getId().equals(id)){
                card.setDescription(desc);
            }
        }

    }

    public void updateAssignee(UUID id, User assignee) {

        for(Card card: cards){
            if(card.getId().equals(id)){
                card.setAssignedUser(assignee);
            }
        }
    }

    public void unassignFromCard(UUID id) {

        for(Card card: cards){
            if(card.getId().equals(id)){
                card.setAssignedUser(null);
            }
        }
    }
}
