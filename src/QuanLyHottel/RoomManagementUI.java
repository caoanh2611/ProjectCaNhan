package QuanLyHottel;

import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RoomManagementUI extends JFrame {
    // khai báo lớp RoomManagementUI kế thừa JFrame (một cửa sổ giao diện)

    private RoomList roomList = new RoomList();
    // tạo một đối tượng roomList để quản lý danh sách các phòng

    private JTextArea displayArea;
    // tạo một vùng văn bản để hiển thị thông tin, kết quả

    private SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    // tạo một định dạng ngày/tháng/năm để định dạng và phân tích dữ liệu ngày

    public RoomManagementUI() {
        // constructor của lớp RoomManagementUI

        setTitle("Room Management System");
        // đặt tiêu đề cho cửa sổ JFrame

        setSize(600, 500);
        // đặt kích thước chiều rộng 600 và chiều cao 500 cho cửa sổ

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // thiết lập khi đóng cửa sổ sẽ kết thúc chương trình

        setLayout(new BorderLayout());
        // sử dụng BorderLayout cho bố cục giao diện

        displayArea = new JTextArea();
        // khởi tạo vùng văn bản để hiển thị thông tin phòng

        add(new JScrollPane(displayArea), BorderLayout.CENTER);
        // thêm vùng văn bản được cuộn (JScrollPane) vào trung tâm của BorderLayout

        JPanel buttonPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        // tạo một panel chứa các nút, sử dụng GridLayout với 4 hàng, 2 cột,
        // khoảng cách ngang dọc giữa các ô là 10

        JButton addRoomBtn = new JButton("Add Room");
        // nút để thêm phòng

        JButton displayBtn = new JButton("Display Rooms");
        // nút để hiển thị danh sách phòng

        JButton findBtn = new JButton("Find Room");
        // nút để tìm phòng theo ID

        JButton deleteBtn = new JButton("Delete Room");
        // nút để xóa phòng theo ID

        JButton lateFeeBtn = new JButton("Calculate Late Fee");
        // nút để tính tiền trễ hạn

        JButton exitBtn = new JButton("Exit");
        // nút để thoát chương trình

        addRoomBtn.addActionListener(e -> addRoom());
        // gắn sự kiện cho nút addRoomBtn, khi bấm gọi phương thức addRoom()

        displayBtn.addActionListener(e -> displayRooms());
        // gắn sự kiện cho nút displayBtn, khi bấm gọi phương thức displayRooms()

        findBtn.addActionListener(e -> findRoom());
        // gắn sự kiện cho nút findBtn, khi bấm gọi phương thức findRoom()

        deleteBtn.addActionListener(e -> deleteRoom());
        // gắn sự kiện cho nút deleteBtn, khi bấm gọi phương thức deleteRoom()

        lateFeeBtn.addActionListener(e -> calculateLateFeeForRoom());
        // gắn sự kiện cho nút lateFeeBtn, khi bấm gọi phương thức calculateLateFeeForRoom()

        exitBtn.addActionListener(e -> System.exit(0));
        // gắn sự kiện cho nút exitBtn, khi bấm sẽ thoát chương trình

        buttonPanel.add(addRoomBtn);
        // thêm nút addRoomBtn vào panel

        buttonPanel.add(displayBtn);
        // thêm nút displayBtn vào panel

        buttonPanel.add(findBtn);
        // thêm nút findBtn vào panel

        buttonPanel.add(deleteBtn);
        // thêm nút deleteBtn vào panel

        buttonPanel.add(lateFeeBtn);
        // thêm nút lateFeeBtn vào panel

        lateFeeBtn.addActionListener(e -> calculateLateFeeForRoom());
        // gắn sự kiện tính phí trễ hạn

        buttonPanel.add(exitBtn);
        // thêm nút exitBtn vào panel

        add(buttonPanel, BorderLayout.SOUTH);
        // đặt panel chứa nút ở phía dưới (South) của BorderLayout

        setVisible(true);
        // hiển thị cửa sổ giao diện
    }

    private void addRoom() {
        // phương thức thêm phòng mới

        String[] options = {"Meeting Room", "Bed Room"};
        // tạo mảng lựa chọn loại phòng: phòng họp (Meeting) hoặc phòng ngủ (Bed)

        int choice = JOptionPane.showOptionDialog(this, "Choose Room Type:", "Add Room",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
        // hiển thị hộp thoại để người dùng chọn loại phòng
        // trả về chỉ số đã chọn trong mảng options

        if (choice == JOptionPane.CLOSED_OPTION) {
            return;
        }
        // nếu người dùng đóng hộp thoại mà không chọn thì kết thúc phương thức

        String id = JOptionPane.showInputDialog(this, "Enter Room ID:");
        // nhập ID phòng từ người dùng
        if (id == null) {
            return;
        }
        // nếu nhấn hủy thì thoát

        String name = JOptionPane.showInputDialog(this, "Enter Room Name:");
        // nhập tên phòng
        if (name == null) {
            return;
        }

        double baseCost = 0;
        // biến lưu chi phí cơ bản
        while (true) {
            String baseStr = JOptionPane.showInputDialog(this, "Enter baseCost:");
            // hiển thị hộp thoại nhập baseCost
            if (baseStr == null) {
                return;
            }
            try {
                baseCost = Double.parseDouble(baseStr);
                // chuyển chuỗi thành số thực
                break;
            } catch (NumberFormatException e) {
                // nếu không phải số thì thông báo lỗi
                JOptionPane.showMessageDialog(this, "Invalid baseCost. Enter a number.");
            }
        }

        Date checkinDate = null;
        // biến lưu ngày check-in
        while (true) {
            String start = JOptionPane.showInputDialog(this, "Enter checkinDate (dd/MM/yyyy):");
            // nhập ngày check-in
            if (start == null) {
                return;
            }
            try {
                df.setLenient(false);
                // không cho phép định dạng ngày mơ hồ
                checkinDate = df.parse(start);
                // parse chuỗi sang ngày
                break;
            } catch (ParseException e) {
                // báo lỗi nếu sai định dạng
                JOptionPane.showMessageDialog(this, "Invalid date. Please enter again (dd/MM/yyyy).");
            }
        }

        Date checkoutDate = null;
        // biến lưu ngày check-out
        while (true) {
            String end = JOptionPane.showInputDialog(this, "Enter checkoutDate (dd/MM/yyyy):");
            // nhập ngày check-out
            if (end == null) {
                return;
            }
            try {
                df.setLenient(false);
                checkoutDate = df.parse(end);
                // parse chuỗi sang ngày
                if (!checkoutDate.before(checkinDate)) {
                    // kiểm tra xem ngày checkout phải bằng hoặc sau ngày checkin
                    break;
                } else {
                    JOptionPane.showMessageDialog(this, "Checkout date must be after checkin date.");
                }
            } catch (ParseException e) {
                JOptionPane.showMessageDialog(this, "Invalid date. Please enter again (dd/MM/yyyy).");
            }
        }

        Room room;
        // biến kiểu Room để lưu phòng tạo mới

        if (choice == 0) {
            // nếu chọn Meeting Room
            int numberOfBeds = 0;
            // biến lưu số giường (không hợp lý lắm cho meeting room, nhưng theo code có sẵn)
            while (true) {
                String nbStr = JOptionPane.showInputDialog(this, "Enter numberOfBeds:");
                if (nbStr == null) {
                    return;
                }
                try {
                    numberOfBeds = Integer.parseInt(nbStr);
                    break;
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "Invalid input. Enter an integer.");
                }
            }
            // tạo đối tượng MeetingRoom
            MeetingRoom mr = new MeetingRoom(numberOfBeds, id, name, baseCost, checkinDate, checkoutDate);
            room = mr;
        } else {
            // nếu chọn Bed Room
            int capacity = 0;
            // biến lưu sức chứa phòng ngủ
            while (true) {
                String capStr = JOptionPane.showInputDialog(this, "Enter capacity:");
                if (capStr == null) {
                    return;
                }
                try {
                    capacity = Integer.parseInt(capStr);
                    break;
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "Invalid input. Enter an integer.");
                }
            }
            // tạo đối tượng BedRoom
            BedRoom br = new BedRoom(capacity, id, name, baseCost, checkinDate, checkoutDate);
            room = br;
        }

        // thêm phòng vào danh sách
        roomList.addRoom(room);

        // hiển thị thông báo thêm thành công
        displayArea.setText("Room added successfully!");
    }

    private void displayRooms() {
        // phương thức hiển thị danh sách phòng
        StringBuilder sb = new StringBuilder();
        // dùng StringBuilder để nối chuỗi hiệu quả
        Iterable<Room> rooms = roomList.getList();
        // lấy danh sách phòng
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        // định dạng ngày
        boolean empty = true;
        // biến kiểm tra xem có phòng nào không

        for (Room room : rooms) {
            // duyệt qua từng phòng
            empty = false;
            sb.append("Id: ").append(room.getId())
                    .append(", Name: ").append(room.getName())
                    .append(", BaseCost: ").append(room.getBasecost())
                    .append(", Check-in: ").append(df.format(room.getCheckinDate()))
                    .append(", Check-out: ").append(df.format(room.getCheckoutDate()));

            // kiểm tra kiểu phòng
            if (room instanceof BedRoom) {
                BedRoom br = (BedRoom) room;
                sb.append(", Capacity: ").append(br.getCapcity());
            } else if (room instanceof MeetingRoom) {
                MeetingRoom mr = (MeetingRoom) room;
                sb.append(", NumberOfBeds: ").append(mr.getNumberOfBeds());
            }
            sb.append("\n");
        }

        // nếu không có phòng
        displayArea.setText(empty ? "No rooms available." : sb.toString());
    }

    private void findRoom() {
        // phương thức tìm phòng theo ID
        String id = JOptionPane.showInputDialog(this, "Enter Room ID:");
        // nhập ID phòng cần tìm
        if (id == null) {
            return;
        }
        Room room = roomList.findRoomById(id);
        // tìm phòng trong danh sách

        if (room == null) {
            // nếu không tìm thấy
            displayArea.setText("Room not found.");
            return;
        }

        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        // định dạng ngày
        StringBuilder sb = new StringBuilder();
        // dựng chuỗi kết quả
        sb.append("Id: ").append(room.getId())
                .append(", Name: ").append(room.getName())
                .append(", BaseCost: ").append(room.getBasecost())
                .append(", Check-in: ").append(df.format(room.getCheckinDate()))
                .append(", Check-out: ").append(df.format(room.getCheckoutDate()));

        if (room instanceof BedRoom) {
            BedRoom br = (BedRoom) room;
            sb.append(", Capacity: ").append(br.getCapcity());
        } else if (room instanceof MeetingRoom) {
            MeetingRoom mr = (MeetingRoom) room;
            sb.append(", NumberOfBeds: ").append(mr.getNumberOfBeds());
        }

        displayArea.setText(sb.toString());
    }

    private void deleteRoom() {
        // phương thức xóa phòng theo ID
        String id = JOptionPane.showInputDialog(this, "Enter Room ID:");
        // nhập ID phòng cần xóa
        if (id == null) {
            return;
        }

        // xóa và hiển thị kết quả
        displayArea.setText(roomList.deleteRoomById(id) ? "Room deleted successfully." : "Room not found.");
    }

    private void calculateLateFeeForRoom() {
        // phương thức tính tiền trễ hạn
        String id = JOptionPane.showInputDialog(this, "Enter Room ID to calculate late fee:");
        // nhập ID phòng cần tính phí trễ
        if (id == null) {
            return;
        }
        Room room = roomList.findRoomById(id);
        // tìm phòng theo id

        if (room == null) {
            // nếu không có phòng
            displayArea.setText("Room not found.");
            return;
        }

        Date dueDate = null;
        // ngày đáo hạn
        while (true) {
            String dueStr = JOptionPane.showInputDialog(this, "Enter due date (dd/MM/yyyy):");
            // nhập ngày đáo hạn
            if (dueStr == null) {
                return;
            }
            try {
                df.setLenient(false);
                dueDate = df.parse(dueStr);
                // chuyển chuỗi sang ngày
                break;
            } catch (ParseException e) {
                JOptionPane.showMessageDialog(this, "Invalid date. Please enter again (dd/MM/yyyy).");
            }
        }

        double lateFeePerDay = 0;
        // phí trễ hạn mỗi ngày
        while (true) {
            String feeStr = JOptionPane.showInputDialog(this, "Enter late fee per day:");
            // nhập phí trễ mỗi ngày
            if (feeStr == null) {
                return;
            }
            try {
                lateFeePerDay = Double.parseDouble(feeStr);
                // chuyển sang số thực
                break;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Invalid input. Enter a number.");
            }
        }

        double lateFee = room.calculateLateFee(dueDate, lateFeePerDay);
        // tính phí trễ hạn

        if (lateFee > 0) {
            // nếu có phí trễ
            displayArea.setText("Late fee for room " + room.getId() + ": " + lateFee);
        } else {
            // nếu không trễ hạn
            displayArea.setText("No late fee for room " + room.getId() + " (not overdue).");
        }
    }

}
