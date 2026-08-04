-- Plaintext Password for every user:
-- Password@123

-- Last Mile Delivery System - Sample Data Insertion Script
-- Respects all constraints and foreign key relationships

-- 1. USERS
INSERT INTO users (id, name, email, phone, password, role, created_at, last_login, is_active) VALUES
(1, 'Aravind Swamy', 'admin@example.com', '9988776655', '$2a$12$sZJOLR1z30BjDvCmlkC0lehRudLM73bxBv0J0h4/XAeUT.MndJcTm', 'ADMIN', NOW(), NOW(), TRUE),
(2, 'Balaji Krishnan', 'dispatcher@example.com', '9876543210', '$2a$12$sZJOLR1z30BjDvCmlkC0lehRudLM73bxBv0J0h4/XAeUT.MndJcTm', 'DISPATCHER', NOW(), NOW(), TRUE),
(3, 'Chandran Pillai', 'agent1@example.com', '9123456780', '$2a$12$sZJOLR1z30BjDvCmlkC0lehRudLM73bxBv0J0h4/XAeUT.MndJcTm', 'DELIVERY_AGENT', NOW(), NOW(), TRUE),
(4, 'Dinesh Kumar', 'agent2@example.com', '9234567891', '$2a$12$sZJOLR1z30BjDvCmlkC0lehRudLM73bxBv0J0h4/XAeUT.MndJcTm', 'DELIVERY_AGENT', NOW(), NOW(), TRUE),
(5, 'Elango Rajan', 'customer1@example.com', '9345678902', '$2a$12$sZJOLR1z30BjDvCmlkC0lehRudLM73bxBv0J0h4/XAeUT.MndJcTm', 'CUSTOMER', NOW(), NOW(), TRUE),
(6, 'Ganesh Shankar', 'customer2@example.com', '9456789013', '$2a$12$sZJOLR1z30BjDvCmlkC0lehRudLM73bxBv0J0h4/XAeUT.MndJcTm', 'CUSTOMER', NOW(), NOW(), TRUE),
(7, 'Hariharan Iyer', 'opsmanager@example.com', '9567890124', '$2a$12$sZJOLR1z30BjDvCmlkC0lehRudLM73bxBv0J0h4/XAeUT.MndJcTm', 'OPERATIONS_MANAGER', NOW(), NOW(), TRUE),
(8, 'Indrajith Menon', 'finance@example.com', '9678901235', '$2a$12$sZJOLR1z30BjDvCmlkC0lehRudLM73bxBv0J0h4/XAeUT.MndJcTm', 'FINANCE', NOW(), NOW(), TRUE);

-- 2. DELIVERY ADDRESSES
INSERT INTO delivery_addresses (id, user_id, address_line1, address_line2, city, state, postal_code, country, is_default) VALUES
(1, 5, '12, Gandhipuram Cross St', 'Near bus stand', 'Coimbatore', 'Tamil Nadu', '641012', 'India', TRUE),
(2, 5, '54, Peelamedu Main Rd', 'Near PSG Tech', 'Coimbatore', 'Tamil Nadu', '641004', 'India', FALSE),
(3, 6, '21A, RS Puram West St', 'Opposite post office', 'Coimbatore', 'Tamil Nadu', '641002', 'India', TRUE),
(4, 6, '105, Saibaba Colony Rd', 'Near temple', 'Coimbatore', 'Tamil Nadu', '641011', 'India', FALSE);

-- 3. DELIVERY AGENTS
INSERT INTO delivery_agents (id, user_id, vehicle_type, current_lat, current_lng, status, rating) VALUES
(1, 3, 'BIKE', 11.0168445, 76.9558321, 'AVAILABLE', 4.7),
(2, 4, 'VAN', 11.0284451, 76.9658321, 'ON_ROUTE', 4.5);

-- 4. ROUTES
INSERT INTO routes (id, agent_id, route_name, start_lat, start_lng, end_lat, end_lng, status, created_at) VALUES
(1, 1, 'North Coimbatore', 11.0183000, 76.9711000, 11.0506000, 76.9922000, 'ACTIVE', NOW()),
(2, 2, 'South Coimbatore', 10.9822000, 76.9611000, 10.9411000, 76.9802000, 'ACTIVE', NOW()),
(3, 2, 'City Express', 11.0111000, 76.9501000, 11.0333000, 76.9702000, 'ACTIVE', NOW());

-- 5. DELIVERY ORDERS
INSERT INTO delivery_orders (id, customer_id, agent_id, tracking_number, pickup_address, delivery_address, status, priority, estimated_delivery_time, actual_delivery_time, created_at) VALUES
(1, 5, 1, 'TRK000000001', 'Peelamedu, Coimbatore', 'Gandhipuram, Coimbatore', 'PENDING', 'HIGH', DATE_ADD(NOW(), INTERVAL 1 DAY), NULL, NOW()),
(2, 6, 2, 'TRK000000002', 'RS Puram, Coimbatore', 'Saibaba Colony, Coimbatore', 'PENDING', 'MEDIUM', DATE_ADD(NOW(), INTERVAL 1 DAY), NULL, NOW()),
(3, 5, 1, 'TRK000000003', 'Saibaba Colony, Coimbatore', 'PSG Tech, Peelamedu, Coimbatore', 'ASSIGNED', 'HIGH', DATE_ADD(NOW(), INTERVAL 1 DAY), NULL, NOW()),
(4, 6, 2, 'TRK000000004', 'Gandhipuram, Coimbatore', 'RS Puram, Coimbatore', 'ASSIGNED', 'LOW', DATE_ADD(NOW(), INTERVAL 2 DAY), NULL, NOW()),
(5, 5, 1, 'TRK000000005', 'PSG Tech, Peelamedu, Coimbatore', 'Saibaba Colony, Coimbatore', 'PICKED_UP', 'MEDIUM', DATE_ADD(NOW(), INTERVAL 1 DAY), NULL, NOW()),
(6, 6, 2, 'TRK000000006', 'Saibaba Colony, Coimbatore', 'Peelamedu, Coimbatore', 'PICKED_UP', 'HIGH', DATE_ADD(NOW(), INTERVAL 1 DAY), NULL, NOW()),
(7, 5, 1, 'TRK000000007', 'Peelamedu, Coimbatore', 'RS Puram, Coimbatore', 'IN_TRANSIT', 'HIGH', DATE_ADD(NOW(), INTERVAL 6 HOUR), NULL, NOW()),
(8, 6, 2, 'TRK000000008', 'RS Puram, Coimbatore', 'Gandhipuram, Coimbatore', 'IN_TRANSIT', 'MEDIUM', DATE_ADD(NOW(), INTERVAL 8 HOUR), NULL, NOW()),
(9, 5, 1, 'TRK000000009', 'PSG Tech, Peelamedu, Coimbatore', 'Gandhipuram, Coimbatore', 'DELIVERED', 'HIGH', NOW(), NOW(), DATE_SUB(NOW(), INTERVAL 4 HOUR)),
(10, 6, 2, 'TRK000000010', 'Saibaba Colony, Coimbatore', 'RS Puram, Coimbatore', 'DELIVERED', 'MEDIUM', NOW(), NOW(), DATE_SUB(NOW(), INTERVAL 5 HOUR)),
(11, 5, 1, 'TRK000000011', 'Saibaba Colony, Coimbatore', 'Peelamedu, Coimbatore', 'FAILED', 'HIGH', NOW(), NULL, DATE_SUB(NOW(), INTERVAL 3 HOUR)),
(12, 6, 2, 'TRK000000012', 'Peelamedu, Coimbatore', 'Saibaba Colony, Coimbatore', 'FAILED', 'LOW', NOW(), NULL, DATE_SUB(NOW(), INTERVAL 6 HOUR)),
(13, 5, 1, 'TRK000000013', 'RS Puram, Coimbatore', 'Gandhipuram, Coimbatore', 'DELIVERED', 'MEDIUM', NOW(), NOW(), DATE_SUB(NOW(), INTERVAL 2 HOUR)),
(14, 6, 2, 'TRK000000014', 'Gandhipuram, Coimbatore', 'Peelamedu, Coimbatore', 'DELIVERED', 'LOW', NOW(), NOW(), DATE_SUB(NOW(), INTERVAL 7 HOUR)),
(15, 5, 1, 'TRK000000015', 'Peelamedu, Coimbatore', 'RS Puram, Coimbatore', 'DELIVERED', 'HIGH', NOW(), NOW(), DATE_SUB(NOW(), INTERVAL 1 HOUR));

-- 6. ROUTE STOPS
INSERT INTO route_stops (id, route_id, order_id, sequence, status, estimated_arrival_time, actual_arrival_time) VALUES
(1, 1, 1, 1, 'PENDING', DATE_ADD(NOW(), INTERVAL 2 HOUR), NULL),
(2, 1, 3, 2, 'PENDING', DATE_ADD(NOW(), INTERVAL 3 HOUR), NULL),
(3, 1, 5, 3, 'PENDING', DATE_ADD(NOW(), INTERVAL 4 HOUR), NULL),
(4, 1, 7, 4, 'PENDING', DATE_ADD(NOW(), INTERVAL 5 HOUR), NULL),
(5, 2, 2, 1, 'PENDING', DATE_ADD(NOW(), INTERVAL 2 HOUR), NULL),
(6, 2, 4, 2, 'PENDING', DATE_ADD(NOW(), INTERVAL 3 HOUR), NULL),
(7, 2, 6, 3, 'PENDING', DATE_ADD(NOW(), INTERVAL 4 HOUR), NULL),
(8, 2, 8, 4, 'PENDING', DATE_ADD(NOW(), INTERVAL 5 HOUR), NULL),
(9, 3, 9, 1, 'VISITED', DATE_SUB(NOW(), INTERVAL 3 HOUR), DATE_SUB(NOW(), INTERVAL 3 HOUR)),
(10, 3, 10, 2, 'VISITED', DATE_SUB(NOW(), INTERVAL 2 HOUR), DATE_SUB(NOW(), INTERVAL 2 HOUR)),
(11, 3, 13, 3, 'VISITED', DATE_SUB(NOW(), INTERVAL 1 HOUR), DATE_SUB(NOW(), INTERVAL 1 HOUR));

-- 7. DELIVERY TRACKING
INSERT INTO delivery_trackings (id, order_id, agent_id, latitude, longitude, updated_time, status) VALUES
(1, 7, 1, 11.0183000, 76.9711000, DATE_SUB(NOW(), INTERVAL 30 MINUTE), 'STARTED'),
(2, 7, 1, 11.0250000, 76.9750000, DATE_SUB(NOW(), INTERVAL 15 MINUTE), 'MOVING'),
(3, 7, 1, 11.0320000, 76.9800000, DATE_SUB(NOW(), INTERVAL 5 MINUTE), 'NEAR_DESTINATION'),
(4, 8, 2, 10.9822000, 76.9611000, DATE_SUB(NOW(), INTERVAL 40 MINUTE), 'STARTED'),
(5, 8, 2, 10.9700000, 76.9650000, DATE_SUB(NOW(), INTERVAL 20 MINUTE), 'MOVING');

-- 8. DELIVERY STATUS HISTORY
INSERT INTO delivery_status_histories (id, order_id, status, comments, updated_time) VALUES
(1, 9, 'PENDING', 'Order placed by customer.', DATE_SUB(NOW(), INTERVAL 5 HOUR)),
(2, 9, 'ASSIGNED', 'Order assigned to agent Chandran.', DATE_SUB(NOW(), INTERVAL 4 HOUR)),
(3, 9, 'PICKED_UP', 'Parcel picked up from hub.', DATE_SUB(NOW(), INTERVAL 3 HOUR)),
(4, 9, 'IN_TRANSIT', 'Agent is on the way.', DATE_SUB(NOW(), INTERVAL 2 HOUR)),
(5, 9, 'DELIVERED', 'Delivered to customer successfully.', DATE_SUB(NOW(), INTERVAL 1 HOUR)),
(6, 10, 'PENDING', 'Order placed by customer.', DATE_SUB(NOW(), INTERVAL 6 HOUR)),
(7, 10, 'ASSIGNED', 'Order assigned to agent Dinesh.', DATE_SUB(NOW(), INTERVAL 5 HOUR)),
(8, 10, 'PICKED_UP', 'Parcel picked up from hub.', DATE_SUB(NOW(), INTERVAL 4 HOUR)),
(9, 10, 'IN_TRANSIT', 'Agent is on the way.', DATE_SUB(NOW(), INTERVAL 3 HOUR)),
(10, 10, 'DELIVERED', 'Delivered to customer successfully.', DATE_SUB(NOW(), INTERVAL 2 HOUR));

-- 9. NOTIFICATIONS
INSERT INTO notifications (id, user_id, order_id, title, message, is_read, created_at) VALUES
(1, 5, 9, 'Order Delivered', 'Your order TRK000000009 has been delivered.', FALSE, NOW()),
(2, 6, 10, 'Order Delivered', 'Your order TRK000000010 has been delivered.', FALSE, NOW()),
(3, 3, 7, 'New Assigned Order', 'You have been assigned order TRK000000007.', FALSE, NOW()),
(4, 4, 8, 'New Assigned Order', 'You have been assigned order TRK000000008.', FALSE, NOW()),
(5, 2, 1, 'New Order Placed', 'A new order TRK000000001 has been registered.', FALSE, NOW());

-- 10. OTP VERIFICATIONS
INSERT INTO otp_verifications (id, order_id, otp_code, is_verified, expired_at, verified_at) VALUES
(1, 9, '123456', TRUE, DATE_ADD(NOW(), INTERVAL 1 HOUR), NOW()),
(2, 1, '654321', FALSE, DATE_ADD(NOW(), INTERVAL 1 HOUR), NULL),
(3, 11, '000000', FALSE, DATE_SUB(NOW(), INTERVAL 2 HOUR), NULL);

-- 11. POD RECORDS
INSERT INTO pod_records (id, order_id, pod_type, pod_data, captured_at) VALUES
(1, 9, 'PHOTO', 'https://s3.ap-south-1.amazonaws.com/lastmile-delivery/pod/photo_order_9.jpg', NOW()),
(2, 10, 'SIGNATURE', 'https://s3.ap-south-1.amazonaws.com/lastmile-delivery/pod/sig_order_10.png', NOW()),
(3, 13, 'OTP', 'OTP verified successfully at delivery', NOW());

-- 12. FAILED DELIVERIES
INSERT INTO failed_deliveries (id, order_id, reason, notes, attempted_time) VALUES
(1, 11, 'Customer unavailable', 'Tried calling 3 times, door locked.', DATE_SUB(NOW(), INTERVAL 1 HOUR)),
(2, 12, 'Wrong address', 'Address does not exist in Coimbatore.', DATE_SUB(NOW(), INTERVAL 2 HOUR));

-- 13. RETURNS
INSERT INTO returns (id, order_id, reason, status, refund_processed, requested_at) VALUES
(1, 9, 'Product damaged during delivery', 'APPROVED', TRUE, DATE_SUB(NOW(), INTERVAL 2 HOUR)),
(2, 10, 'Received wrong item', 'REQUESTED', FALSE, DATE_SUB(NOW(), INTERVAL 1 HOUR));

-- 14. AGENT PAYOUTS
INSERT INTO agent_payouts (id, agent_id, amount, status, transaction_reference, paid_at) VALUES
(1, 1, 550.00, 'PAID', 'TXN-ARV12345678', NOW()),
(2, 2, 750.00, 'PENDING', NULL, NULL),
(3, 1, 200.00, 'FAILED', 'TXN-ARV88888888', NULL);

-- 15. AUDIT LOGS
INSERT INTO audit_logs (id, user_id, action, details, ip_address, timestamp) VALUES
(1, 1, 'Login', 'Admin logged in successfully.', '192.168.1.100', DATE_SUB(NOW(), INTERVAL 10 HOUR)),
(2, 1, 'User Creation', 'Created agent account Chandran Pillai.', '192.168.1.100', DATE_SUB(NOW(), INTERVAL 9 HOUR)),
(3, 2, 'Order Creation', 'Created order TRK000000001.', '192.168.1.101', DATE_SUB(NOW(), INTERVAL 8 HOUR)),
(4, 2, 'Order Assignment', 'Assigned order TRK000000003 to agent Chandran.', '192.168.1.101', DATE_SUB(NOW(), INTERVAL 7 HOUR)),
(5, 3, 'Status Update', 'Updated status of order TRK000000009 to DELIVERED.', '192.168.1.105', DATE_SUB(NOW(), INTERVAL 4 HOUR)),
(6, 3, 'POD Capture', 'Captured photo POD signature for order TRK000000009.', '192.168.1.105', DATE_SUB(NOW(), INTERVAL 3 HOUR)),
(7, 5, 'Return Request', 'Submitted return request for order TRK000000009.', '192.168.1.110', DATE_SUB(NOW(), INTERVAL 2 HOUR));
