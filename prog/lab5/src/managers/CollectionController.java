package managers;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

import exceptions.InvalidDataException;
import lab5.Constants;
import models.Status;
import models.Worker;
import utils.WorkerValidators;

/**
 * Class which completes all operations with Collection of workers
 *
 */
public class CollectionController {
    /**
     * Collection of workers, which we operate on
     */
    private PriorityQueue<Worker> collection;
    /**
     * Collection's creation date
     * <p>In fact it is equal to CollectionManager object creation date
     */
    public LocalDateTime creationDate;
    /**
     * Boolean value which is true if collection was change after last saving or loading from data file
     */
    private boolean changeFlag;

    /**
     * CollectionController constructor
     * <p>Completes initialization of collection, generate creationDate and set changeFlag to false
     * @param collection
     */
    public CollectionController(PriorityQueue<Worker> collection) {
        this.collection = collection;
        this.creationDate = LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault());
        this.changeFlag = false;
    }

    /**
     * Method to check if collection contains valid values
     * <p>Used to validate input collection from data file
     * <p>Firstly it checks if all id are unique
     * <p>Then it validate all fields using WorkerValidator
     * @return
     */
    public static boolean isValid(PriorityQueue<Worker> collection){
        Set<Integer> idSet = collection.stream().map(Worker::getId).collect(Collectors.toSet());
        if(idSet.size() != collection.size()) return false;
        for(Worker worker : collection){
            try {
                WorkerValidators.workerValidator.validate(worker);
            } catch (InvalidDataException e){
                return false;
            }
        }
        return true;
    }

    /**
     * Method to check if current collection isn't saved in data file
     * @return boolean value
     */
    public boolean wasChanged(){
        return this.changeFlag;
    }

    /**
     * Method to set changeFlag to false value
     */
    public void removeChangeFlag(){
        this.changeFlag = false;
    }

    /**
     * Method to generate unique id for new element of collection
     * <p>It gets the maximum id in current collection and then increments it
     * @return id
     */
    public Integer generateId(){
        if(this.collection.isEmpty()) return 1;
        return this.collection
                .stream()
                .map(Worker::getId)
                .max(Integer::compareTo).get() + 1;
    }

    /**
     * Method to get the collection
     *
     * @return Collection of workers
     */
    public PriorityQueue<Worker> getCollection() {
        return this.collection;
    }

    /**
     * Method to get the creation date of the class object
     *
     * @return The creation date of the collection
     */
    public LocalDateTime getCreationDate(){
        return this.creationDate;
    }

    /**
     * This method check if collection contain any element with id equal to given
     * @param id to compare with
     * @return true if element was found, else false
     */
    public boolean containsId(Integer id){
        if(this.collection.isEmpty()) return false;
        return this.collection.stream().anyMatch(worker -> worker.getId() == id);
    }

    /**
     * Method to get information about collection (type of elements, creation date, collection size)
     * @return Formatted string
     */
    public String getInfo() {
        return "Type: " + this.collection.getClass().getName() +
            "\nCreation date: " + this.creationDate.format(Constants.formatter) +
            "\nSize: " + this.collection.size();
    }

    /**
     * Add new object to collection
     *
     * @param newWorker Object to add
     */
    public void add(Worker newWorker){
        this.collection.add(newWorker);

        this.changeFlag = true;
    }

    /**
     * Updates value of collection element by it's id
     *
     * @param id Element's id
     * @param newWorker New value for the element
     */
    public void update(Integer id, Worker newWorker){
        removeById(id);
        newWorker.setId(id);
        add(newWorker);
    }

    /**
     * Removes element with given id from collection
     *
     * @param id Element's id
     */
    public void removeById(Integer id){
        this.collection.removeIf(worker -> worker.getId() == id);

        this.changeFlag = true;
    }

    /**
     * Clear collection
     */
    public void clear(){
        this.collection.clear();
        this.changeFlag = true;
    }

    /**
     * Prints the first element in the collection
     */
    public Worker getHead(){
        return this.collection.peek();
    }

    /**
     * Removes all elements which are greater that given
     * @param worker Element to compare with
     * @return Number of deleted elements
     */
    public int removeGreater(Worker worker){
        int oldSize = this.collection.size();
        //this.collection.removeIf(worker1 -> worker1.compareTo(worker) > 0);
        this.collection.removeIf(w -> w.getSalary() > worker.getSalary());

        this.changeFlag = true;

        return oldSize - this.collection.size();
    }
    /**
     * Finds a worker with the specified status.
     */
    public List<Worker> getByStatus(Status statustToFind) {
    	return this.collection
                .stream()
                .filter(worker ->statustToFind.equals(worker.getStatus()))
                .toList();
    }
    /**
     * Removes all elements with given status
     * @return Number of deleted elements
     */
    public int removeAnyByStatus(Status statustToFind){
        int oldSize = this.collection.size();
        this.collection.removeIf(worker ->statustToFind.equals(worker.getStatus()));
        this.changeFlag = true;

        return oldSize - this.collection.size();
    }

    /**
     * Method to get worker with maximal salary
     *
     * @return Worker whose salary is maximal
     */
    public Worker getMaxBySalary(){
        return this.collection
                .stream()
                .max(Comparator.comparing(Worker::getSalary))
                .orElseThrow(NoSuchElementException::new);
    }
    /**
     * Prints all unique salaries.
     */
    public HashSet<Float> printUniqueSalary() {
    	HashSet<Float> uniqueSalaries = new HashSet<>();
    	for (Worker worker : collection) {
    		uniqueSalaries.add(worker.getSalary());
    	}
    	return uniqueSalaries;
    }
}