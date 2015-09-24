package org.bojarski.sozz.model.domain.requisition;

/**
 * Typ wyliczeniowy przechowujący statusy jakie może przyjmować zapotrzebowanie.
 * @author Arkadiusz Bojarski
 *
 */
public enum Status {
    PLACED,
    ORDERED,
    REALIZED,
    CANCELED
}
