import hashlib
import time

class Block:
    def __init__(self, version, prev_hash, timestamp, bits, nonce, tx_root):
        self.version = version
        self.prev_hash = prev_hash
        self.timestamp = timestamp
        self.bits = bits
        self.nonce = nonce
        self.tx_root = tx_root

    def hash_block(self):
        block_header = (str(self.version) + str(self.prev_hash) +
                        str(self.timestamp) + str(self.bits) + str(self.nonce) + str(self.tx_root))
        return hashlib.sha256(block_header.encode()).hexdigest()

def generate_genesis_block():
    version = 1
    prev_hash = "0" * 32
    timestamp = int(time.time())
    bits = 0x1e7fffff   
    nonce = 0
    tx_root = ""
    return Block(version, prev_hash, timestamp, bits, nonce, tx_root)

def generate_next_block(prev_block, nonce):
    version = prev_block.version
    prev_hash = prev_block.hash_block()
    timestamp = int(time.time())
    bits = prev_block.bits
    tx_root = prev_hash

    return Block(version, prev_hash, timestamp, bits, nonce, tx_root)

def main():
    genesis_block = generate_genesis_block()
    print("Genesis Block Hash:", genesis_block.hash_block())

    prev_block = genesis_block
    for i in range(1, 6):
        nonce = i * 123
        next_block = generate_next_block(prev_block, nonce)
        print(f"Block {i} Hash:", next_block.hash_block())
        prev_block = next_block

if __name__ == "__main__":
    main()
