import { Building } from "./building.model";

export class Room {
    // Note: Using only optional constructor properties without backing store disables typescript's type checking for the type
    constructor(roomId?: number, RoomName?: string, available?: boolean, capacity?: number, building?: Building) {

        this.roomId = roomId;
        this.RoomName = RoomName;
        this.available = available;
        this.capacity = capacity;
        this.building = building;
    }

    public roomId: number;
    public RoomName: string;
    public available: boolean;
    public capacity: number;
    public building: Building;
}
