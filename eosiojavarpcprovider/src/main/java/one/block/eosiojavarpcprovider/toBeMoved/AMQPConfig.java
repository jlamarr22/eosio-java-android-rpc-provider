package one.block.eosiojavarpcprovider.toBeMoved;

/**
 * The config class for AMQP
 */
public class AMQPConfig {

    /**
     * The request queue name.
     */
    private String requestQueueName;

    /**
     * The reply-to queue name.
     */
    private String replyToQueueName; //TODO::Determine whether or not this is even configurable

    /**
     * The host
     */
    private String hostName;

    /**
     * Instantiates a new AMQP config.
     *
     * @param requestQueueName the requestQueueName
     * @param replyToQueueName the replyToQueueName
     * @param hostName the hostName
     */
    public AMQPConfig(String requestQueueName, String replyToQueueName, String hostName) {
        this.setRequestQueueName(requestQueueName);
        this.setReplyToQueueName(replyToQueueName);
        this.setHostName(hostName);
    }

    /**
     * Gets requestQueueName.
     *
     * @return the requestQueueName
     */
    public String getRequestQueueName() {
        return this.requestQueueName;
    }

    /**
     * Sets requestQueueName.
     *
     * @param requestQueueName the requestQueueName
     */
    public void setRequestQueueName(String requestQueueName) {
        this.requestQueueName = requestQueueName;
    }

    /**
     * Gets replyToQueueName.
     *
     * @return the replyToQueueName
     */
    public String getReplyToQueueName() {
        return this.replyToQueueName;
    }

    /**
     * Sets replyToQueueName.
     *
     * @param replyToQueueName the replyToQueueName
     */
    public void setReplyToQueueName(String replyToQueueName) {
        this.replyToQueueName = replyToQueueName;
    }

    /**
     * Gets hostName.
     *
     * @return the hostName
     */
    public String getHostName() {
        return this.hostName;
    }

    /**
     * Sets hostName.
     *
     * @param hostName the hostName
     */
    public void setHostName(String hostName) {
        this.hostName = hostName;
    }
}
