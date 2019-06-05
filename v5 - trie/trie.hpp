#include <iostream>


#define CHAR_SIZE 128

class Trie
{
public:
	bool isLeaf;
    float fo;
	Trie* character[CHAR_SIZE];

	Trie()
	{
		this->isLeaf = false;
        this->fo = 0;
		for (int i = 0; i < CHAR_SIZE; i++)
			this->character[i] = nullptr;
	}

	void insert(std::string, float);
	float search(std::string);
    void prettyPrint();
};


void Trie::insert(std::string key, float f)
{
	Trie* curr = this;
	for (int i = 0; i < key.length(); i++)
	{
		
		if (curr->character[key[i]] == nullptr)
			curr->character[key[i]] = new Trie();

		curr = curr->character[key[i]];
	}

	curr->isLeaf = true;
    this->fo = f;
}

float Trie::search(std::string key)
{
	if (this == nullptr)
		return -1;

	Trie* curr = this;
	for (int i = 0; i < key.length(); i++)
	{
		curr = curr->character[key[i]];

		if (curr == nullptr)
			return -1;
	}

	return curr->fo;
}

void Trie::prettyPrint(){

    if(this != nullptr){
    
        if(this->isLeaf)
            std::cout << this->fo << "  ";
        
        for(int i = 0; i < CHAR_SIZE; i++)
            this->character[i]->prettyPrint();
    }
        
}
