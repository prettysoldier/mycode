package algorithm.stack_queue.dog_cat_queue;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author shuaijunhe
 * @create 2019/9/23
 * @description
 */
public class DogCatQueue {
    private Queue<DogCatEnter> dogQueue = new LinkedList<>();
    private Queue<DogCatEnter> catQueue = new LinkedList<>();


    public void add(Pet pet){
        if(pet.getType().equals("dog")){
            dogQueue.add(new DogCatEnter(pet, System.currentTimeMillis()));
        }
        if(pet.getType().equals("cat")){
            catQueue.add(new DogCatEnter(pet, System.currentTimeMillis()));
        }
    }

    public Pet pollAll() throws Exception{
        if(!dogQueue.isEmpty() && !catQueue.isEmpty()){

            return dogQueue.peek().getCount() > dogQueue.peek().getCount() ? dogQueue.poll().getPet() :
                    catQueue.poll().getPet();
        }
        if(!dogQueue.isEmpty()){
            return dogQueue.poll().getPet();
        }
        if(!catQueue.isEmpty()){
            return catQueue.poll().getPet();
        }
        throw new Exception("queue is empty");
    }

    public Pet pollDog() throws Exception{
        if(dogQueue.isEmpty()){
            throw new Exception("Dog queue is empty");
        }
        return dogQueue.poll().getPet();
    }

    public Pet pollCat() throws Exception{
        if(!catQueue.isEmpty()){
            throw new Exception("Cat queue is empty");
        }
        return catQueue.poll().getPet();
    }


}

class DogCatEnter{
    private Pet pet;
    private long count;

    public DogCatEnter(Pet pet, long count) {
        this.pet = pet;
        this.count = count;
    }

    public long getCount() {
        return count;
    }

    public Pet getPet() {
        return pet;
    }
}

class Pet{
    private String type;

    public Pet(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}

class Dog extends Pet{
    public Dog(String type) {
        super("dog");
    }
}

class Cat extends Pet{
    public Cat(String type) {
        super("cat");
    }
}