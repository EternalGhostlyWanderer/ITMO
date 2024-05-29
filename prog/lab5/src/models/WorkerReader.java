package models;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Date;

import exceptions.InvalidDataException;
import lab5.Constants;
import managers.CollectionController;
import utils.Console;
import utils.WorkerParsers;
import utils.WorkerValidators;
import utils.YesNoQuestionAsker;

/**
 * Class with methods to read all fields of Worker class
 * @see Worker
 */
public class WorkerReader extends ValueReader {
    /**
     * Object of collection controller which is used to generate valid id while reading new Worker class element
     */
    private final CollectionController collectionController;
    public WorkerReader(CollectionController collectionController) {
        this.collectionController = collectionController;
    }

    /**
     * Method to read Worker from user input
     * @return Worker object
     * @throws InvalidDataException If input is wrong and script mode is on
     */
    public Worker readWorker() throws InvalidDataException {
        Integer id = this.collectionController.generateId();
        String name = readName();
        Coordinates coordinates = readCoordinates();
        LocalDateTime creationDate = LocalDateTime.now();
        Float salary = readSalary();
        ZonedDateTime startDate = readStartDate();
        Date endDate = readEndDate();
        Status status = readStatus();
        Organization organization = readOrganization();
        return new Worker(id, name, coordinates, creationDate, salary, startDate, endDate, status, organization);
    }

    /**
     * Method to read workers name
     * @return String name
     * @throws InvalidDataException If input is wrong and script mode is on
     */
    public String readName() throws InvalidDataException {
        return readValue("name", WorkerValidators.nameValidator, WorkerParsers.stringParser);
    }

    /**
     * Method to read workers coordinates
     * @return Coordinates
     * @throws InvalidDataException If input is wrong and script mode is on
     */
    public Coordinates readCoordinates() throws InvalidDataException {
        return new Coordinates(readX(), readY());
    }

    /**
     * Method to read x coordinate
     * @return double value
     * @throws InvalidDataException If input is wrong and script mode is on
     */
    public long readX() throws InvalidDataException {
        return readValue("x coordinate", WorkerValidators.xValidator, WorkerParsers.longParser);
    }

    /**
     * Method to read y coordinate
     * @return double value
     * @throws InvalidDataException If input is wrong and script mode is on
     */
    public double readY() throws InvalidDataException {
        return readValue("y coordinate", WorkerValidators.yValidator, WorkerParsers.doubleParser);
    }

    /**
     * Method to read workers salary
     * @return Integer value of salary
     * @throws InvalidDataException If input is wrong and script mode is on
     */
    public Float readSalary() throws InvalidDataException {
        return readValue("salary", WorkerValidators.salaryValidator, WorkerParsers.floatParser);
    }

    /**
     * Method to read workers start date
     * @return LocalDateTime value of start date
     * @throws InvalidDataException If input is wrong and script mode is on
     */
    public ZonedDateTime readStartDate() throws InvalidDataException {
        return readValue("start date (yyyy-MM-dd)", WorkerValidators.startDateValidator, WorkerParsers.zonedDateTimeParser);
    }

    /**
     * Method to read workers end date
     *
     * <p>Before reading user is asked if worker has endDate
     * @return LocalDateTime value of endDate or null if it is no endDate
     * @throws InvalidDataException If input is wrong and script mode is on
     */
    public Date readEndDate() throws InvalidDataException {
        YesNoQuestionAsker questionAsker = new YesNoQuestionAsker("Does worker has end date?");
        if(!questionAsker.ask()) return null;
        return readValue("end date (" + Constants.DATE_FORMAT_STRING + ")", WorkerValidators.endDateValidator, WorkerParsers.dateParser);
    }

    /**
     * Method to read workers status
     * <p>Before reading method prints all possible values of status
     *
     * @return Status value
     * @throws InvalidDataException If input is wrong and script mode is on
     */
    public Status readStatus() throws InvalidDataException {
        if(!Constants.SCRIPT_MODE) {
            Console.getInstance().printLn("List of possible status values:");
            for (Status i : Status.values()) {
                Console.getInstance().printLn(i);
            }
        }
        return readValue("status", WorkerValidators.statusValidator, WorkerParsers.statusParser);
    }

    /**
     * Method to read workers organization
     * <p>Before reading user is asked if worker has organization
     * @return Organization value or null if worker doesn't have organization
     * @throws InvalidDataException If input is wrong and script mode is on
     */
    public Organization readOrganization() throws InvalidDataException {
        YesNoQuestionAsker questionAsker = new YesNoQuestionAsker("Does worker has organization?");
        if(!questionAsker.ask()) return null;
        return new Organization(readFullName(), readOrganizationType());
    }

    /**
     * Method to read organizations fullName
     * @return Long value of fullName
     * @throws InvalidDataException If input is wrong and script mode is on
     */
    public String readFullName() throws InvalidDataException {
        return readValue("fullName", WorkerValidators.organizationNameValidator, WorkerParsers.stringParser);
    }

    /**
     * Method to read organizations organizationType
     * <p>Before reading method asks if organization has organizationType and if answer is yes all possible OrganizationTypes are printed
     * @return OrganizationType value
     * @throws InvalidDataException If input is wrong and script mode is on
     */
    public OrganizationType readOrganizationType() throws InvalidDataException {
        YesNoQuestionAsker questionAsker = new YesNoQuestionAsker("Does organization has type?");
        if(!questionAsker.ask()) return null;
        if(!Constants.SCRIPT_MODE) {
            Console.getInstance().printLn("List of possible organization types values:");
            for (OrganizationType i : OrganizationType.values()) {
                Console.getInstance().printLn(i);
            }
        }
        return readValue("organization type", WorkerValidators.organizationTypeValidator, WorkerParsers.organizationTypeParser);
    }


}