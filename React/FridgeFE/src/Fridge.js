import React from 'react';
import Results from './Results';
import { Input, Header, List, Icon, Transition, Button } from 'semantic-ui-react'


class Fridge extends React.Component {
    constructor(props) {
        super(props);
        this.state = { 
            value: '',
            ingredients: []
        };
        
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }
  
    handleChange(event) {
        this.setState({value: event.target.value});
    }
  
    handleSubmit(event) {
        const newIngredients = [...this.state.ingredients, this.state.value];
        this.setState({ingredients: newIngredients})
        this.setState({value:''})
    
        event.preventDefault();
    }

    removeIngredient(ingredient) {
        const ingredients = this.state.ingredients;

        this.setState({
            ingredients: ingredients.filter((i) => {
                return i !== ingredient
              }),
        });
    }

    handleSearch() {
        const ingredients = this.state.ingredients;
        
        this.props.onChange(ingredients)
    }
    
    render() {

        const ingredients = this.state.ingredients
      
        return (
        <div>
          <Header as='h3'> 냉장고 </Header>

          <form onSubmit={this.handleSubmit}>
          <Input 
            type="text"
            placeholder='재료추가'
            value={this.state.value}
            onChange={this.handleChange}
          />
          </form>

          {/* TODO: Maybe change to labels? */}
          <List selection verticalAlign='middle'>
          <Transition.Group as={List} duration={200} divided size='medium' verticalAlign='middle'>
              {ingredients.map((ingredient) => (
                <List.Item>
                  <Icon color='orange' name='minus circle' onClick={() => this.removeIngredient(ingredient)}/>
                  <List.Content>
                    <List.Header>{ingredient}</List.Header>
                  </List.Content>
                </List.Item>
              ))}
            </Transition.Group>
          </List>

          <Button compact color='green' onClick={() => this.handleSearch()}>레서피들 보기</Button>

        </div>
      );
    }
  }

export default Fridge;
